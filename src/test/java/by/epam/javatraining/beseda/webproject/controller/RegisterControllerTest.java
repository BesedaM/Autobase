package by.epam.javatraining.beseda.webproject.controller;

import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.CUSTOMER_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.CUSTOMER_REGISTER_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.USER_DATA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class RegisterControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private RegisterController registerController;


    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
    }


//	@Test
//	public void testGoToRegisterCustomerPage_correct() throws Exception {
//		this.mockMvc.perform(get("/register/customer"))
//        .andExpect(status().isOk())
//        .andExpect(view().name(CUSTOMER_REGISTER_PAGE));
//	}


    @Test
    public void testRegisterCustomerPage_correct() throws Exception {

        User user = new User();
        Customer customer = new Customer();

        when(userService.loginExists(anyString())).thenReturn(false);
        when(userService.createEntity(anyString(), anyString(), anyString()))
                .thenReturn(user);
        doNothing().when(userService).add(any(User.class));

        when(customerService.createCustomer(any(User.class), anyString(), anyString()
                , anyString(), anyString(),
				anyString(), anyString())).thenReturn(customer);
        doNothing().when(customerService).add(any(Customer.class));

        this.mockMvc.perform(post("/register/customer/add")
                .param(LOGIN, "stevia")
                .param(PASSWORD, "12345huM")
                .param(PASSWORD_CONFIRM, "12345huM")
                .param(COMPANY_NAME, "ZAO 'Stevia")
                .param(NAME, "Ирина")
                .param(SURNAME, "Гончарова")
                .param(PHONE, "(033)158-87-99")
                .param(EMAIL, "i.goncharova@stevia.by"))
                .andExpect(status().isOk())
                .andExpect(view().name(CUSTOMER_MAIN_PAGE))
                .andExpect(request().sessionAttribute(USER_DATA, user));
    }

    @Test
    public void testRegisterCustomerPage_loginExists() throws Exception {

        when(userService.loginExists(anyString())).thenReturn(true);

        this.mockMvc.perform(post("/register/customer/add")
                .param(LOGIN, "admin")
                .param(PASSWORD, "12345huM")
                .param(PASSWORD_CONFIRM, "12345huM")
                .param(COMPANY_NAME, "ZAO 'Stevia")
                .param(NAME, "Ирина")
                .param(SURNAME, "Гончарова")
                .param(PHONE, "(033)158-87-99")
                .param(EMAIL, "i.goncharova@stevia.by"))
                .andExpect(status().isOk())
                .andExpect(view().name(CUSTOMER_REGISTER_PAGE))
                .andExpect(request().attribute(ERROR_REGISTER_LOGIN,STATUS_TRUE));
    }

    @Test
    public void testRegisterCustomerPage_passwordsDontMatch() throws Exception {

        when(userService.loginExists(anyString())).thenReturn(false);

        this.mockMvc.perform(post("/register/customer/add")
                .param(LOGIN, "stevia")
                .param(PASSWORD, "12345huM")
                .param(PASSWORD_CONFIRM, "12345hUM")
                .param(COMPANY_NAME, "ZAO 'Stevia")
                .param(NAME, "Ирина")
                .param(SURNAME, "Гончарова")
                .param(PHONE, "(033)158-87-99")
                .param(EMAIL, "i.goncharova@stevia.by"))
                .andExpect(status().isOk())
                .andExpect(view().name(CUSTOMER_REGISTER_PAGE))
                .andExpect(request().attribute(ERROR_REGISTER_PASSWORD,STATUS_TRUE));
    }


    @Test
    public void testRegisterCustomerPage_unsafePassword() throws Exception {

        when(userService.loginExists(anyString())).thenReturn(false);

        this.mockMvc.perform(post("/register/customer/add")
                .param(LOGIN, "stevia")
                .param(PASSWORD, "12345")
                .param(PASSWORD_CONFIRM, "12345")
                .param(COMPANY_NAME, "ZAO 'Stevia")
                .param(NAME, "Ирина")
                .param(SURNAME, "Гончарова")
                .param(PHONE, "(033)158-87-99")
                .param(EMAIL, "i.goncharova@stevia.by"))
                .andExpect(status().isOk())
                .andExpect(view().name(CUSTOMER_REGISTER_PAGE))
                .andExpect(request().attribute(UNSAFE_PASSWORD,STATUS_TRUE));
    }


}
