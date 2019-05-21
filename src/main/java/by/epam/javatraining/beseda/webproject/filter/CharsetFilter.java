package by.epam.javatraining.beseda.webproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;


@WebFilter(filterName = "CharsetFilter", urlPatterns = "/*",
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8"),
                @WebInitParam(name="responseContentType",value="text/html; charset=UTF-8")})
public class CharsetFilter implements Filter {

    private String encoding;
    private String respContType;

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
        respContType=config.getInitParameter("responseContentType");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {

        String codeRequest = request.getCharacterEncoding();
        if (encoding != null && !codeRequest.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(encoding);
            response.setContentType(respContType);
            response.setCharacterEncoding(encoding);
        }
        next.doFilter(request, response);
    }

    @Override
    public void destroy() {
        encoding = null;
        respContType=null;
    }
}
