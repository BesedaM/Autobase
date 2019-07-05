package by.epam.javatraining.beseda.webproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPSessionAttribute.USER_ROLE;

@WebFilter(filterName = "NewUserFilter", urlPatterns = "/view/*")
public class NewUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getSession(false) == null
                || req.getSession().getAttribute(USER_ROLE) == null) {
            request.getRequestDispatcher(req.getServletPath()).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }

    }
}
