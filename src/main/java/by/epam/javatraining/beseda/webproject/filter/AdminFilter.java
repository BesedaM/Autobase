package by.epam.javatraining.beseda.webproject.filter;

import by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPPath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPSessionAttribute.USER_ROLE;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEnumTable.USER_ADMIN;

@WebFilter(filterName = "AdminFilter", urlPatterns = "/view/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (USER_ADMIN.equals(req.getSession().getAttribute(USER_ROLE))) {
//            chain.doFilter(request, response);
            req.getRequestDispatcher(req.getPathInfo()).forward(request,response);
        } else {
            req.getSession().invalidate();
            request.getRequestDispatcher(JSPPath.LOGIN_PAGE.getPath()).forward(request, response);
        }
    }
}
