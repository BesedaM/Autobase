package by.epam.javatraining.beseda.webproject.controller;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.LANGUAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.LANGUAGE_EN;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.LANGUAGE_RU;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.LOCALE_EN;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.LOCALE_FILE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.LOCALE_RU;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ResponseBody
public class LocaleChangerController {

	@PostMapping(value= {"/change_locale"})
	public ModelAndView changeLocale(@RequestParam String language_select, @RequestParam String current_page ,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(current_page);
		String locale = null;
		session.setAttribute(LANGUAGE, language_select);
		if (language_select.equals(LANGUAGE_EN)) {
			locale = LOCALE_EN;
		} else if (language_select.equals(LANGUAGE_RU)) {
			locale = LOCALE_RU;
		}
		session.setAttribute(LOCALE_FILE, locale);
		return mav;
	}

}
