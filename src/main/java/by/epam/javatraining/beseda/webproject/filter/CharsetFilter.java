package by.epam.javatraining.beseda.webproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "CharsetFilter",urlPatterns = "/*")
public class CharsetFilter implements Filter {

    private boolean active = false;
    private String encoding;


    @Override
    public void init(FilterConfig config) throws ServletException {
        String act = config.getInitParameter("active");
        if (act != null) {
            active = act.equalsIgnoreCase("true");
        }

        encoding = config.getInitParameter("encoding");
        if (encoding == null) encoding = "UTF-8";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        if (active) {
            if (request.getCharacterEncoding() == null) {
                request.setCharacterEncoding(encoding);
            }

            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
        }
        next.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
