package by.epam.javatraining.beseda.webproject.controller;

import by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
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
import java.util.List;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.CHANGE_CAR;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.CHANGING_ROUTE;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminCarsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CarService carService;

    @Mock
    private RouteService routeService;

    @InjectMocks
    private AdminCarsController adminCarsController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminCarsController).build();
    }

    @Test
    public void testChangeCarState() throws Exception {
        this.mockMvc.perform(post("/admin/cars/change_state")
                .param(CURRENT_PAGE, ADMIN_MAIN_PAGE)
                .param(CAR_STATE_CHANGER, "good")
                .param(CAR_ID, "15"))
                .andExpect(status().isOk())
                .andExpect(view().name(ADMIN_MAIN_PAGE));

        verify(carService, times(1)).updateCarState(anyInt(), anyString());
    }

    @Test
    public void testReloadCarsInRoutes() throws Exception {
        this.mockMvc.perform(post("/admin/cars/reload_cars_in_routes")
                .param(CURRENT_PAGE, NEW_REQUESTS_PAGE)
                .param(CAR_ID, "15"))
                .andExpect(status().isOk())
                .andExpect(view().name(NEW_REQUESTS_PAGE))
                .andExpect(model().attributeExists(CAR_ID));
    }

    @Test
    public void testChangeCarListFlag() throws Exception {
        this.mockMvc.perform(post("/admin/cars/change_cars_list")
                .param(CURRENT_PAGE, NEW_REQUESTS_PAGE))
                .andExpect(status().isOk())
                .andExpect(view().name(NEW_REQUESTS_PAGE))
                .andExpect(request().sessionAttribute(CHANGE_CAR, JSPSessionAttribute.STATUS_TRUE));
    }

    @Test
    public void testChangeCars_dataChanged() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cars_id", "1");
        params.add("cars_id", "3");
        Route route = new Route();
        List<Integer> previousCars = new ArrayList<>();
        previousCars.add(1);
        previousCars.add(2);

        when(routeService.getCarsId(anyInt())).thenReturn(previousCars);


        this.mockMvc.perform(post("/admin/cars/change_cars")
                .param(CURRENT_PAGE, CHANGE_ROUTE_PAGE)
                .params(params)
                .sessionAttr(CHANGING_ROUTE, route))
                .andExpect(status().isOk())
                .andExpect(view().name(CHANGE_ROUTE_PAGE));

        verify(routeService, times(1)).deleteCar(anyInt(), anyInt());
        verify(routeService, times(1)).addCar(anyInt(), anyInt());
    }

    @Test
    public void testChangeCars_dataNotChanged() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cars_id", "1");
        params.add("cars_id", "3");
        Route route = new Route();
        List<Integer> previousCars = new ArrayList<>();
        previousCars.add(1);
        previousCars.add(3);

        when(routeService.getCarsId(anyInt())).thenReturn(previousCars);

        this.mockMvc.perform(post("/admin/cars/change_cars")
                .param(CURRENT_PAGE, CHANGE_ROUTE_PAGE)
                .params(params)
                .sessionAttr(CHANGING_ROUTE, route))
                .andExpect(status().isOk())
                .andExpect(view().name(CHANGE_ROUTE_PAGE));

        verify(routeService, times(0)).deleteCar(anyInt(), anyInt());
        verify(routeService, times(0)).addCar(anyInt(), anyInt());
    }

}