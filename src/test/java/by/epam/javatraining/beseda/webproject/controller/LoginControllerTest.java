package by.epam.javatraining.beseda.webproject.controller;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.ERROR_MESSAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.STATUS_TRUE;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import by.epam.javatraining.beseda.webproject.config.TestConfig;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfig.class})
@WebAppConfiguration("src/main/java")
public class LoginControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
    }

    @Test
    public void testGoToLoginPage_correct() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

//    @Test
//    public void testGoToLoginPage_error() throws Exception {
//        this.mockMvc.perform(get("/login?error"))
//                .andExpect(status().isOk())
//                .andExpect(request().attribute(ERROR_MESSAGE, STATUS_TRUE));
//    }


}
