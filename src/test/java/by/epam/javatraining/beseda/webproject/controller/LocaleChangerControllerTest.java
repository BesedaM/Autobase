package by.epam.javatraining.beseda.webproject.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.LANGUAGE_SELECT;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class LocaleChangerControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LocaleChangerController()).build();
    }

    @Test
    public void testChangeLocale_EN() throws Exception {
        this.mockMvc.perform(post("/change_locale")
                .param(LANGUAGE_SELECT, LANGUAGE_EN)
                .param(CURRENT_PAGE, "login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(request().sessionAttribute(LANGUAGE, LANGUAGE_EN))
                .andExpect(request().sessionAttribute(LOCALE_FILE, LOCALE_EN));
    }

    @Test
    public void testChangeLocale_RU() throws Exception {
        this.mockMvc.perform(post("/change_locale")
                .param(LANGUAGE_SELECT, LANGUAGE_RU)
                .param(CURRENT_PAGE, "login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(request().sessionAttribute(LANGUAGE, LANGUAGE_RU))
                .andExpect(request().sessionAttribute(LOCALE_FILE, LOCALE_RU));
    }

}