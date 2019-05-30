package by.epam.javatraining.beseda.webproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.USER_CUSTOMER;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPPath.LOGIN_PAGE;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPSessionAttribute.USER_ROLE;

@WebFilter(filterName = "CustomerFilter", urlPatterns = "/*")         //change patterns!!!!
public class CustomerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getSession().getAttribute(USER_ROLE) == USER_CUSTOMER) {
            chain.doFilter(request, response);
        } else {
            req.getSession().invalidate();
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }

    }
}
