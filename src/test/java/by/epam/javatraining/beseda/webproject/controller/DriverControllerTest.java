package by.epam.javatraining.beseda.webproject.controller;

import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RouteService;
import by.epam.javatraining.beseda.webproject.service.processors.DriverProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.DRIVER_MAIN_PAGE;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class DriverControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DriverProcessor driverProcessor;

    @Mock
    private CarService carService;

    @Mock
    private RouteService routeService;

    @InjectMocks
    private DriverController driverController;


    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(driverController).build();
    }


    @Test
    public void testChangeRouteStatusCarState() throws Exception {

        doNothing().when(carService).updateCarState(anyInt(),anyString());
        doNothing().when(routeService).updateRouteStatus(anyInt(),anyString());
        doNothing().when(driverProcessor).processDriverData(any(HttpSession.class));

        this.mockMvc.perform(post("/driver/driver_main/update_car_route")
        .param(ROUTE_STATUS_CHANGER,"planned")
        .param(CAR_STATE_CHANGER,"normal")
        .param(CAR_ID,"125")
        .param(ROUTE_ID,"15"))
                .andExpect(status().isOk())
                .andExpect(view().name(DRIVER_MAIN_PAGE));
    }

    @Test
    public void testChangeRouteStatus() throws Exception {

        doNothing().when(routeService).updateRouteStatus(anyInt(),anyString());
        doNothing().when(driverProcessor).processDriverData(any(HttpSession.class));

        this.mockMvc.perform(post("/driver/driver_main/update_route")
                .param(ROUTE_STATUS_CHANGER,"planned")
                .param(ROUTE_ID,"15"))
                .andExpect(status().isOk())
                .andExpect(view().name(DRIVER_MAIN_PAGE));

    }
}