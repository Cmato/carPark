package cz.muni.fi.pa165.springmvc.filters;

/**
*
* @author xruzic16
*/

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebFilter(urlPatterns = { "/car/*"})
public class AccessFilter implements Filter {

    final static Logger log = LoggerFactory.getLogger(AccessFilter.class);


    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;

        Object authenticatedUser = request.getSession().getAttribute("authenticatedUser");
        if (authenticatedUser != null) {
            chain.doFilter(request, response);
            return;
        }

            log.warn("Unauthorized Access");
            response.sendRedirect(request.getContextPath()+"/login");
            //chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}