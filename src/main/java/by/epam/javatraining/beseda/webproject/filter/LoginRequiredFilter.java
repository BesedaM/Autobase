package by.epam.javatraining.beseda.webproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPPath.LOGIN_PAGE;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPSessionAttribute.IS_VALID_USER;

@WebFilter(filterName = "LoginRequiredFilter", urlPatterns = "/*")
public class LoginRequiredFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getSession().getAttribute(IS_VALID_USER) == "true") {
            chain.doFilter(request, response);
        } else {
            req.getSession().invalidate();
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }

    }
}
