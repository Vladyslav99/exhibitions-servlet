package my.exhibitions.servlet.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {

    private static final Logger log = Logger.getLogger(SessionLocaleFilter.class);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        log.info("Locale filtering invoked");

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getParameter("sessionLocale") != null) {
            req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
        }
        chain.doFilter(request, response);
    }
}
