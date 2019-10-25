package by.epam.javatraining.beseda.webproject.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.LANGUAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.LOCALE_FILE;
import static by.epam.javatraining.beseda.webproject.interceptors.InterceptorsConstants.DEFAULT_LANGUAGE;
import static by.epam.javatraining.beseda.webproject.interceptors.InterceptorsConstants.DEFAULT_LOCALE;


@Component
public class LocaleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession httpSession=request.getSession();
        if (httpSession.getAttribute(LANGUAGE) == null) {
            httpSession.setAttribute(LANGUAGE, DEFAULT_LANGUAGE);
            httpSession.setAttribute(LOCALE_FILE, DEFAULT_LOCALE);
        }

    }
}
