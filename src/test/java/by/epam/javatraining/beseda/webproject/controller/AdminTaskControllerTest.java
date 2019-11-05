package by.epam.javatraining.beseda.webproject.controller;

import by.epam.javatraining.beseda.webproject.entity.route.Address;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.service.entityservice.AddressService;
import by.epam.javatraining.beseda.webproject.service.entityservice.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.ADD_NEW_ROUTE_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(MockitoJUnitRunner.class)
public class AdminTaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AdminTaskController adminTaskController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminTaskController).build();
    }

    @Test
    public void testAddTask() throws Exception {
        this.mockMvc.perform(post("/admin/tasks/add_task")
                .param(CURRENT_PAGE, ADD_NEW_ROUTE_PAGE))
                .andExpect(request().sessionAttribute(ADD_TASK_FLAG, STATUS_TRUE))
                .andExpect(status().isOk())
                .andExpect(view().name(ADD_NEW_ROUTE_PAGE));
    }

    @Test
    public void testDeleteTask() throws Exception {
        Route route = new Route();

        doNothing().when(taskService).delete(anyInt());

        this.mockMvc.perform(post("/admin/tasks/delete_task")
                .param(CURRENT_PAGE, ADD_NEW_ROUTE_PAGE)
                .param(ID, "2")
                .sessionAttr(CHANGING_ROUTE, route))
                .andExpect(status().isOk())
                .andExpect(view().name(ADD_NEW_ROUTE_PAGE));
    }

    @Test
    public void testChangeTask() throws Exception {
        Route route = new Route();
        Task task01 = new Task();
        Task task02 = new Task();
        task01.setId(1);
        task02.setId(2);
        route.addTask(task01);
        route.addTask(task02);

        this.mockMvc.perform(post("/admin/tasks/change_task")
                .param(CURRENT_PAGE, ADD_NEW_ROUTE_PAGE)
                .param(ID, "2")
                .sessionAttr(CHANGING_ROUTE, route))
                .andExpect(request().sessionAttribute(TASK_TO_CHANGE, task02))
                .andExpect(status().isOk())
                .andExpect(view().name(ADD_NEW_ROUTE_PAGE));
    }

    @Test
    public void testProcessTask_changingTask() throws Exception {
        Task task = new Task();
        Route route=new Route();
        doNothing().when(addressService).add(any(Address.class));
        when(taskService.createTask(any(GregorianCalendar.class), anyString())).thenReturn(task);
        doNothing().when(taskService).setAddress(anyInt(),anyInt());
        when(addressService.createAddress(anyString(),anyString(),anyString(),anyString(),anyInt(),anyString())).thenReturn(new Address());

        this.mockMvc.perform(post("/admin/tasks/process_task")
                .param(CURRENT_PAGE, ADD_NEW_ROUTE_PAGE)
                .param(ROUTE_ID, "2")
                .param(DETAILS, "details")
                .param(COUNTRY, "Беларусь")
                .param(DISTRICT, "Минский")
                .param(CITY, "Минск")
                .param(STREET, "Ульяновская")
                .param(HOUSE, "19")
                .param(BUILDING, "")
                .param(DATE, "2019-12-23")
                .param(TIME, "11:20")
                .sessionAttr(ADD_TASK_FLAG,STATUS_TRUE)
                .sessionAttr(TASK_TO_CHANGE,task)
                .sessionAttr(CHANGING_ROUTE,route)

        )
                .andExpect(status().isOk())
                .andExpect(view().name(ADD_NEW_ROUTE_PAGE));

    }


    @Test
    public void testProcessTask_addingTaskToRoute() throws Exception {
        Task task = new Task();
        Route route=new Route();
        doNothing().when(addressService).add(any(Address.class));
        when(taskService.createTask(any(GregorianCalendar.class), anyString())).thenReturn(task);
        doNothing().when(taskService).setAddress(anyInt(),anyInt());
        when(addressService.createAddress(anyString(),anyString(),anyString(),anyString(),anyInt(),anyString())).thenReturn(new Address());

        this.mockMvc.perform(post("/admin/tasks/process_task")
                .param(CURRENT_PAGE, ADD_NEW_ROUTE_PAGE)
                .param(ROUTE_ID, "2")
                .param(DETAILS, "details")
                .param(COUNTRY, "Беларусь")
                .param(DISTRICT, "Минский")
                .param(CITY, "Минск")
                .param(STREET, "Ульяновская")
                .param(HOUSE, "19")
                .param(BUILDING, "")
                .param(DATE, "2019-12-23")
                .param(TIME, "11:20")
                .sessionAttr(ADD_TASK_FLAG,STATUS_TRUE)
                .sessionAttr(CHANGING_ROUTE,route)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(ADD_NEW_ROUTE_PAGE));
    }


    @Test
    public void testProcessTask_addingNewTask() throws Exception {
        Task task = new Task();
        Task task01=new Task();
        Task task02=new Task();

        List<Task> taskList=new ArrayList<>();
        taskList.add(task);
        taskList.add(task01);
        taskList.add(task02);

        Route route=new Route();
        doNothing().when(addressService).add(any(Address.class));
        when(taskService.createTask(any(GregorianCalendar.class), anyString())).thenReturn(task);
        doNothing().when(taskService).setAddress(anyInt(),anyInt());
        when(addressService.createAddress(anyString(),anyString(),anyString(),anyString(),anyInt(),anyString())).thenReturn(new Address());

        this.mockMvc.perform(post("/admin/tasks/process_task")
                .param(CURRENT_PAGE, ADD_NEW_ROUTE_PAGE)
                .param(ROUTE_ID, "2")
                .param(DETAILS, "details")
                .param(COUNTRY, "Беларусь")
                .param(DISTRICT, "Минский")
                .param(CITY, "Минск")
                .param(STREET, "Ульяновская")
                .param(HOUSE, "19")
                .param(BUILDING, "")
                .param(DATE, "2019-12-23")
                .param(TIME, "11:20")
                .sessionAttr(CHANGING_ROUTE,route)
                .sessionAttr(TASK_LIST,taskList)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(ADD_NEW_ROUTE_PAGE));
    }
}