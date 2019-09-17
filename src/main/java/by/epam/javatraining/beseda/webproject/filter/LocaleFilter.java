package by.epam.javatraining.beseda.webproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.LANGUAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.LOCALE_FILE;

@WebFilter(filterName = "LocaleFilter", urlPatterns = { "*.jsp", "*.html" }, initParams = {
		@WebInitParam(name = "default_locale", value = "locale_en"),
		@WebInitParam(name = "default_language", value = "en") })
public class LocaleFilter implements Filter {

	private String defaultLocale;
	private String defaultLanguage;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		defaultLocale = filterConfig.getInitParameter("default_locale");
		defaultLanguage = filterConfig.getInitParameter("default_language");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(true);

		if (session.getAttribute(LANGUAGE) == null) {
			session.setAttribute(LANGUAGE, defaultLanguage);
			session.setAttribute(LOCALE_FILE, defaultLocale);
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		defaultLocale = null;
		defaultLanguage = null;
	}
}