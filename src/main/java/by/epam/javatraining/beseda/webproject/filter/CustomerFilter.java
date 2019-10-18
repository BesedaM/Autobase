package by.epam.javatraining.beseda.webproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.LOGIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.USER_ROLE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_CUSTOMER;

@WebFilter(filterName = "CustomerFilter", urlPatterns = "/view/customer/*")
public class CustomerFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		if (USER_CUSTOMER.equals(req.getSession().getAttribute(USER_ROLE))) {
			chain.doFilter(request, response);
		} else {
			req.getSession().invalidate();
			request.getRequestDispatcher("/view/login.jsp").forward(request, response);
		}

	}
}