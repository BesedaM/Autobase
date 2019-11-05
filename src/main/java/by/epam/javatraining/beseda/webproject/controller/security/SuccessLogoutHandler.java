package by.epam.javatraining.beseda.webproject.controller.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.LANGUAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.LOCALE_FILE;

@Component
public class SuccessLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        HttpSession oldSession = request.getSession();
        String lang = (String) oldSession.getAttribute(LANGUAGE);
        String locFile = (String) oldSession.getAttribute(LOCALE_FILE);
        oldSession.invalidate();

        HttpSession newSession = request.getSession(true);
        newSession.setAttribute(LANGUAGE, lang);
        newSession.setAttribute(LOCALE_FILE, locFile);
    }

}
