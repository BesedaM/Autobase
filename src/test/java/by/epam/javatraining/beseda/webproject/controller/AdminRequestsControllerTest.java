package by.epam.javatraining.beseda.webproject.controller;

import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.RouteBuilder;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.service.processors.CarsDataProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.REQUEST_CUSTOMER_MAP;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class AdminRequestsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RequestService requestService;

    @Mock
    private CustomerService customerService;

    @Mock
    private CarsDataProcessor carsDataProcessor;

    @Mock
    private RouteBuilder routeBuilder;


    @InjectMocks
    private AdminRequestsController adminRequestsController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminRequestsController).build();
    }

    @Test
    public void testGetNewRequestsPage() throws Exception {
        List<Request> requestList = new ArrayList<>();
        requestList.add(new Request());
        requestList.add(new Request());

        when(requestService.getNewRequests()).thenReturn(requestList);

        this.mockMvc.perform(post("/admin/new_requests"))
                .andExpect(status().isOk())
                .andExpect(view().name(NEW_REQUESTS_PAGE));

        verify(requestService, times(2)).getCustomerId(anyInt());
        verify(customerService, times(2)).getEntityById(anyInt());
    }

    @Test
    public void testGetCurrentRequestsPage() throws Exception {
        List<Request> requestList = new ArrayList<>();
        Route route = new Route();
        Customer customer = new Customer();
        requestList.add(new Request());
        requestList.add(new Request());

        when(requestService.getCurrentRequests()).thenReturn(requestList);
        when(routeBuilder.getEntity(anyInt())).thenReturn(route);
        when(requestService.getCustomerId(route.getId())).thenReturn(1);
        when(customerService.getEntityById(1)).thenReturn(customer);

        this.mockMvc.perform(post("/admin/current_requests"))
                .andExpect(status().isOk())
                .andExpect(view().name(CURRENT_REQUESTS_PAGE));

        verify(routeBuilder, times(2)).addCarList(any(Route.class));
    }

    @Test
    public void testGotoCurrentRequestsPage() throws Exception {
        this.mockMvc.perform(post("/admin/current_requests_redirect"))
                .andExpect(status().isOk())
                .andExpect(view().name(CURRENT_REQUESTS_PAGE));

        verify(carsDataProcessor).processCarsData(any(HttpSession.class));
    }

    @Test
    public void testGetFulfilledRequestsPage() throws Exception {
        List<Request> requestList = new ArrayList<>();
        requestList.add(new Request());
        requestList.add(new Request());

        Route route = new Route();

        when(requestService.getFulfilledRequests()).thenReturn(requestList);
        when(routeBuilder.getEntity(anyInt())).thenReturn(route);

        this.mockMvc.perform(post("/admin/fulfilled_requests"))
                .andExpect(status().isOk())
                .andExpect(view().name(FULFILLED_REQUESTS_PAGE));

        verify(routeBuilder, times(2)).addCarList(route);
        verify(requestService, times(2)).getCustomerId(anyInt());
        verify(customerService, times(2)).getEntityById(anyInt());
    }

    @Test
    public void testGetRejectedRequestsPage() throws Exception {

        List<Request> requestList = new ArrayList<>();
        requestList.add(new Request());
        requestList.add(new Request());

        Route route = new Route();

        when(requestService.getRejectedRequests()).thenReturn(requestList);

        this.mockMvc.perform(post("/admin/rejected_requests"))
                .andExpect(status().isOk())
                .andExpect(view().name(REJECTED_REQUESTS_PAGE));

        verify(requestService, times(2)).getCustomerId(anyInt());
        verify(customerService, times(2)).getEntityById(anyInt());
    }

    @Test
    public void testProcessRequest() throws Exception {

        Customer customer01 = new Customer();
        Customer customer02 = new Customer();

        Request req01 = new Request(customer01,"123456");
        Request req02 = new Request(customer02,"987456");
        req01.setId(2);

        Map<Request, Customer> requestCustomerMap = new TreeMap<>();
        requestCustomerMap.put(req01, customer01);
        requestCustomerMap.put(req02, customer02);

        this.mockMvc.perform(post("/admin/new_requests/process_request")
                .param(CURRENT_PAGE, CURRENT_REQUESTS_PAGE)
                .param(ID, "2")
                .param(STATUS, "any")
                .sessionAttr(REQUEST_CUSTOMER_MAP, requestCustomerMap))
                .andExpect(status().isOk())
                .andExpect(view().name(CURRENT_REQUESTS_PAGE));

        verify(requestService,times(1)).update(any(Request.class));
    }

//    @Test
//    public void gotoMain()  throws Exception {
//        this.mockMvc.perform(get("/admin/admin_main"))
//                .andExpect(status().isOk())
//                .andExpect(view().name(ADMIN_MAIN_PAGE));
//    }
}