package by.epam.javatraining.beseda.webproject.controller;

import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.service.entityservice.UserService;
import by.epam.javatraining.beseda.webproject.service.processors.CustomerProcessor;
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

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.ID;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.CUSTOMER_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.CUSTOMER_PERSONAL_DATA_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.REQUEST_LIST;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.USER_DATA;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerProcessor customerProcessor;

    @Mock
    private CustomerService customerService;

    @Mock
    private RequestService requestService;

    @Mock
    private UserService userService;

    @InjectMocks
    private CustomerController customerController;


    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }


//    @Test
//    public void gotoCustomerMain() {
//    }

    @Test
    public void testGoToCustomerPersonalData() throws Exception {
        this.mockMvc.perform(get("/customer/customer_personal_data"))
                .andExpect(status().isOk())
                .andExpect(view().name(CUSTOMER_PERSONAL_DATA_PAGE));
    }

    @Test
    public void testDeleteRequest() throws Exception {
        doNothing().when(requestService).delete(anyInt());
        doNothing().when(customerProcessor).processCustomerData(any(HttpSession.class));

        this.mockMvc.perform(post("/customer/delete_request")
                .param(ID, "12")
                .param(CURRENT_PAGE, CUSTOMER_MAIN_PAGE))
                .andExpect(status().isOk())
                .andExpect(view().name(CUSTOMER_MAIN_PAGE));
    }

    @Test
    public void testAddRequest() throws Exception {
        Request request = new Request();
        Customer customer = new Customer();

        when(requestService.createRequest(customer, "1234589 abcde")).thenReturn(request);

        this.mockMvc.perform(post("/customer/add_request")
                .param(NEW_REQUEST_TEXT, "1234589 abcde")
                .sessionAttr(USER_DATA, customer))
                .andExpect(status().isOk())
                .andExpect(view().name(CUSTOMER_MAIN_PAGE));

        verify(requestService, times(1)).add(request);
    }

    @Test
    public void testAddAnotherRequest() throws Exception {
        Request request = new Request();
        List<Request> requestList = new ArrayList<>();

        this.mockMvc.perform(post("/customer/add_another_request")
                .sessionAttr(REQUEST_LIST, requestList)
                .sessionAttr(NEW_REQUEST, request))
                .andExpect(status().isOk())
                .andExpect(view().name(CUSTOMER_MAIN_PAGE));
    }

    @Test
    public void testAddChangeData_correct() throws Exception {
        Customer customer = new Customer();

        this.mockMvc.perform(post("/customer/change_data")
                .param(CURRENT_PAGE, CUSTOMER_PERSONAL_DATA_PAGE)
                .sessionAttr(USER_DATA, customer)
                .param(COMPANY_NAME, "OAO 'Taxi'")
                .param(NAME, "Tatiana")
                .param(SURNAME, "Igoreva")
                .param(PHONE, "028 877 77 44")
                .param(EMAIL, "t.igoreva@mail.ru"))
                .andExpect(status().isOk())
                .andExpect(view().name(CUSTOMER_PERSONAL_DATA_PAGE))
                .andExpect(request().attribute(DATA_CHANGED, STATUS_TRUE));

        verify(customerService,times(1)).update(customer);
    }

    @Test
    public void testAddChangeData_incorrect() throws Exception {
        Customer customer = new Customer();

        this.mockMvc.perform(post("/customer/change_data")
                .param(CURRENT_PAGE, CUSTOMER_PERSONAL_DATA_PAGE)
                .sessionAttr(USER_DATA, customer)
                .param(COMPANY_NAME, "")
                .param(NAME, "Tatiana")
                .param(SURNAME, "Igoreva")
                .param(PHONE, "028 877 77 44")
                .param(EMAIL, "t.igoreva@mail.ru"))
                .andExpect(status().isOk())
                .andExpect(view().name(CUSTOMER_PERSONAL_DATA_PAGE));

        verify(customerService, never()).update(customer);
    }


    @Test
    public void testChangePassword_correct() throws Exception {

        Customer customer = new Customer();

        this.mockMvc.perform(post("/customer/change_password")
                .param(CURRENT_PAGE, CUSTOMER_PERSONAL_DATA_PAGE)
                .sessionAttr(USER_DATA, customer)
                .param(NEW_PASSWORD, "123456qweRTY")
                .param(PASSWORD, "TatIAna45")
                .param(PASSWORD_CONFIRM, "TatIAna45"))
                .andExpect(status().isOk())
                .andExpect(view().name(CUSTOMER_PERSONAL_DATA_PAGE))
                .andExpect(request().attribute(PASSWORD_CHANGED, STATUS_TRUE));

        verify(userService, times(1)).changePassword(anyInt(), anyString());
    }

    @Test
    public void testChangePassword_incorrectActualPassword() throws Exception {
        Customer customer = new Customer();

        this.mockMvc.perform(post("/customer/change_password")
                .param(CURRENT_PAGE, CUSTOMER_PERSONAL_DATA_PAGE)
                .sessionAttr(USER_DATA, customer)
                .param(NEW_PASSWORD, "123456qweRTY")
                .param(PASSWORD, "TatIAna45")
                .param(PASSWORD_CONFIRM, "TatIAna"))
                .andExpect(status().isOk())
                .andExpect(view().name(CUSTOMER_PERSONAL_DATA_PAGE))
                .andExpect(request().attribute(ERROR_MESSAGE, STATUS_TRUE));

        verify(userService, never()).changePassword(anyInt(), anyString());
    }

    @Test
    public void testChangePassword_illegalNewPassword() throws Exception {
        Customer customer = new Customer();

        this.mockMvc.perform(post("/customer/change_password")
                .param(CURRENT_PAGE, CUSTOMER_PERSONAL_DATA_PAGE)
                .sessionAttr(USER_DATA, customer)
                .param(NEW_PASSWORD, "123456qwe")
                .param(PASSWORD, "TatIAna45")
                .param(PASSWORD_CONFIRM, "TatIAna45"))
                .andExpect(status().isOk())
                .andExpect(view().name(CUSTOMER_PERSONAL_DATA_PAGE))
                .andExpect(request().attribute(ERROR_MESSAGE, STATUS_TRUE));

        verify(userService, never()).changePassword(anyInt(), anyString());
    }

}