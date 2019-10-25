package by.epam.javatraining.beseda.webproject.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.javatraining.beseda.webproject.interceptors.InterceptorsConstants.PROJECT_ENCODING;
import static by.epam.javatraining.beseda.webproject.interceptors.InterceptorsConstants.RESPONSE_CONTENT_TYPE;

@Component
public class CharsetSetter extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        request.setCharacterEncoding(PROJECT_ENCODING);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.setCharacterEncoding(PROJECT_ENCODING);
    }


}
