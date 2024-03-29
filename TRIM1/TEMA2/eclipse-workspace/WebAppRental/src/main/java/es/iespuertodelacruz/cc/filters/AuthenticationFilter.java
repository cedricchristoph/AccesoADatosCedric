package es.iespuertodelacruz.cc.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.cc.webapprental.entity.Staff;
import es.iespuertodelacruz.cc.webapprental.utils.Globals;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/home/*")
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest http = (HttpServletRequest) request;
		Staff user = (Staff) http.getAttribute(Globals.ATT_SESSION_LOGGED_USER);
		if (user != null) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(Globals.SERVLET_LOGIN);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
