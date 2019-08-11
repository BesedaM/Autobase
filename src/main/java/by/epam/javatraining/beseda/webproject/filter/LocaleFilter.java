package by.epam.javatraining.beseda.webproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPSessionAttribute.LANGUAGE;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPSessionAttribute.LOCALE_FILE;

@WebFilter(filterName = "LocaleFilter", urlPatterns = {"*.jsp", "*.html"},
        initParams = {@WebInitParam(name = "default_locale", value = "locale_en"),
                @WebInitParam(name = "default_language", value = "en")})
public class LocaleFilter implements Filter {

    private String default_locale;
    private String default_language;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        default_locale = filterConfig.getInitParameter("default_locale");
        default_language = filterConfig.getInitParameter("default_language");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);

        if (session.getAttribute(LANGUAGE) == null) {
            session.setAttribute(LANGUAGE, default_language);
            session.setAttribute(LOCALE_FILE, default_locale);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        default_locale = null;
        default_language = null;
    }
}
