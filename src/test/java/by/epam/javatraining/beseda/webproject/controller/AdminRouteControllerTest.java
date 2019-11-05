package by.epam.javatraining.beseda.webproject.controller;

import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RouteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.CUSTOMER;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.ID;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.NEW_REQUEST_TEXT;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminRouteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @Mock
    private RouteService routeService;

    @InjectMocks
    private AdminRouteController adminRouteController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminRouteController).build();
    }

    @Test
    public void testGotoAddNewRoute() throws Exception {
        Customer customer = new Customer();
        when(customerService.getEntityById(anyInt())).thenReturn(customer);

        this.mockMvc.perform(post("/admin/create_route")
                .param(ID, "1")
                .param(CUSTOMER_ID, "3")
                .param(REQUEST_TEXT, "text"))
                .andExpect(status().isOk())
                .andExpect(view().name(ADD_NEW_ROUTE_PAGE))
                .andExpect(request().sessionAttribute(NEW_REQUEST_ID, Integer.parseInt("1")))
                .andExpect(request().sessionAttribute(NEW_REQUEST_TEXT, "text"));
    }

    @Test
    public void testAddNewRoute() throws Exception {
        Customer customer = new Customer();
        Route route = new Route();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cars_id", "1");
        params.add("cars_id", "3");

        when(routeService.createRoute(anyInt(),anyString())).thenReturn(route);

        this.mockMvc.perform(post("/admin/add_new_route")
                .param(ID, "1")
                .param(ROUTE_NAME, "route name")
                .param(CURRENT_PAGE,ADD_NEW_ROUTE_PAGE)
                .params(params))
                .andExpect(status().isOk())
                .andExpect(view().name(ADD_NEW_ROUTE_PAGE))
                .andExpect(request().sessionAttribute(CURRENT_ROUTE, route))
                .andExpect(request().sessionAttribute(TASK_LIST, new ArrayList<Task>()));

        verify(routeService,times(1)).add(any(Route.class));

    }

    @Test
    public void testAddChangeRoute() throws Exception {
        Customer customer01 = new Customer();
        Customer customer02 = new Customer();

        Route route = new Route();

        Request req01 = new Request(customer01,"123456");
        Request req02 = new Request(customer02,"987456");
        req01.setId(2);
        req01.setRoute(route);

        Map<Request, Customer> requestCustomerMap = new TreeMap<>();
        requestCustomerMap.put(req01, customer01);
        requestCustomerMap.put(req02, customer02);

        this.mockMvc.perform(post("/admin/change_route")
                .param(ID, "2")
                .sessionAttr(REQUEST_CUSTOMER_MAP, requestCustomerMap))
                .andExpect(status().isOk())
                .andExpect(view().name(CHANGE_ROUTE_PAGE))
                .andExpect(request().sessionAttribute(CHANGING_ROUTE, route));
    }
}