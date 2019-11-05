package by.epam.javatraining.beseda.webproject.controller;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.ERROR_MESSAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.STATUS_TRUE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.LOGIN_PAGE;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = {"/", "/login"})
    public ModelAndView gotoLoginPage(Model model, 
    		@RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute(ERROR_MESSAGE, STATUS_TRUE);
        }
        return new ModelAndView(LOGIN_PAGE);
    }

}
