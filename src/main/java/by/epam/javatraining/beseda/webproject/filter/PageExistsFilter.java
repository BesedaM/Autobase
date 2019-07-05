package by.epam.javatraining.beseda.webproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPPath.ALL_PATHS;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPPath.LOGIN_PAGE;

@WebFilter(filterName = "PageExistsFilter",urlPatterns = {"/view/*"})
public class PageExistsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String path = req.getServletPath();

        if (ALL_PATHS.contains(path)) {
            chain.doFilter(request, response);
        } else {
            req.getSession().invalidate();
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }

}
