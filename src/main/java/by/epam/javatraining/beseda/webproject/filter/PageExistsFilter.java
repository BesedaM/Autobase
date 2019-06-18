package by.epam.javatraining.beseda.webproject.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPPath.ALL_PATHES;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPPath.LOGIN_PAGE;

public class PageExistsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String path = req.getServletPath();

        if (ALL_PATHES.contains(path)) {
            chain.doFilter(request, response);
        } else {
            req.getSession().invalidate();
            request.getRequestDispatcher("/" + LOGIN_PAGE).forward(request, response);
        }
    }

}
