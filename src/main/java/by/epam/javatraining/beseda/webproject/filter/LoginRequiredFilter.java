package by.epam.javatraining.beseda.webproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "LoginRequiredFilter", urlPatterns = "/lll")      //change url Pattern!!!!
public class LoginRequiredFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getSession().getAttribute("isValidUser") == "true") {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("LoginServlet").forward(request, response);
        }

    }
}
