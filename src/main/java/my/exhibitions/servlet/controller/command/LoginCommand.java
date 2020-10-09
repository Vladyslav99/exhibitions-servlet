package my.exhibitions.servlet.controller.command;

import my.exhibitions.servlet.model.entity.User;
import my.exhibitions.servlet.model.service.ServiceFactory;
import my.exhibitions.servlet.model.service.UserService;
import my.exhibitions.servlet.util.PasswordEncodingUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command{

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final UserService userService = serviceFactory.createUserService();

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("fieldsRequired", "All fields required");
            return "/WEB-INF/jsp/login.jsp";
        }

        Optional<User> userOptional =
                userService.findUserByUsernameAndPassword(username, PasswordEncodingUtil.encode(password));

        if (!userOptional.isPresent()){
            request.setAttribute("wrongUsernamePassword", "Wrong username or password!");
            return "/WEB-INF/jsp/login.jsp";
        }
        User user = userOptional.get();
        if (CommandUtility.isUserLogged(request, user)){
            request.setAttribute("loggedUser", "User has already logged!");
            return "/WEB-INF/jsp/login.jsp";
        }
        CommandUtility.setUser(request, user);
        return "redirect:/app/";
    }
}
