package by.epam.javatraining.beseda.webproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;


@WebFilter(filterName = "CharsetFilter", urlPatterns = {"*.jsp", "*.html"},     //
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8"),
                @WebInitParam(name = "responseContentType", value = "text/html; charset=UTF-8")})
public class CharsetFilter implements Filter {

    private String encoding;
    private String respContType;

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
        respContType = config.getInitParameter("responseContentType");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {

        if (encoding != null && respContType != null) {
            request.setCharacterEncoding(encoding);
            response.setContentType(respContType);
            response.setCharacterEncoding(encoding);
        }
        next.doFilter(request, response);
    }

    @Override
    public void destroy() {
        encoding = null;
        respContType = null;
    }
}
