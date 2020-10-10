package my.exhibitions.servlet.controller.filter;

import my.exhibitions.servlet.model.entity.RoleType;
import my.exhibitions.servlet.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/app/*")
public class AuthenticationFilter implements Filter {

    private static final Logger log = Logger.getLogger(AuthenticationFilter.class);

    private final Map<RoleType, List<String>> authenticatedOnly = new HashMap<>();
    private final List<String> notAuthenticated = new ArrayList<>();
    private final List<String> commons = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initialization");
        commons.addAll(Arrays.asList("/app/", "/app"));
        authenticatedOnly.put(RoleType.ADMIN, Arrays.asList("/app/admin/add-exhibition-event",
                "/app/admin/exhibition-event-list", "/app/user/exhibition-event-list", "/app/logout",
                "/app/admin/update-exhibition-event-status", "/app/admin/statistics", "/app/user/purchase-successful"));
        authenticatedOnly.put(RoleType.CUSTOMER, Arrays.asList("/app/user/exhibition-event-list",
                "/app/user/purchase-successful", "/app/logout"));
        notAuthenticated.addAll(Arrays.asList("/app/login", "/app/registration"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (hasPermission(request)){
            log.info("Has permissions");
            filterChain.doFilter(request, response);
        }else {
            log.warn("Has no permissions");
            request.setAttribute("errorMessage", "Access denied");
            request.getRequestDispatcher("/app/error").forward(request, response);
        }
    }

    private boolean hasPermission(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();

        User user = (User) session.getAttribute("user");
        String path = httpServletRequest.getRequestURI();

        if (commons.contains(path))
            return true;

        if (user == null){
            return notAuthenticated.contains(path);
        }
        return authenticatedOnly.get(user.getRole().getRoleType()).contains(path);
    }
}
