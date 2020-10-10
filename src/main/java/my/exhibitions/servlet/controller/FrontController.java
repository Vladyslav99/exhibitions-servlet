package my.exhibitions.servlet.controller;

import my.exhibitions.servlet.controller.command.*;
import my.exhibitions.servlet.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrontController extends HttpServlet {

    private static final Logger log = Logger.getLogger(FrontController.class);

    private final Map<String, Command> commands = new HashMap<>();
    private final List<User> loggedUsers = new ArrayList<>();

    @Override
    public void init(ServletConfig config) {
        log.info("Initialization");
        commands.put("/app/login", new LoginCommand());//replace null
        commands.put("/app/logout", new LogoutCommand());
        commands.put("/app/registration", new RegistrationCommand());//replace null
        commands.put("/app/error", new ErrorCommand());

        commands.put("/app/admin/add-exhibition-event", new AdminAddExhibitionEvent());
        commands.put("/app/admin/exhibition-event-list", new AdminExhibitionEventList());
        commands.put("/app/admin/update-exhibition-event-status", new AdminUpdateExhibitionEventStatus());
        commands.put("/app/admin/statistics", new AdminStatisticsCommand());

        commands.put("/app/user/exhibition-event-list", new ExhibitionEventList());
        commands.put("/app/user/purchase-successful", new OrderCommand());

        config.getServletContext().setAttribute("loggedUsers", loggedUsers);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info(String.format("Servlet method %s was invoked", request.getMethod()));
        String path = request.getRequestURI();

        Command command = commands.getOrDefault(path, (r) -> "/index.jsp");

        String page = command.execute(request);

        if (page.contains("redirect:")) {
            response.sendRedirect(page.replaceAll("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
