package my.exhibitions.servlet.controller.command;

import my.exhibitions.servlet.dto.UserDTO;
import my.exhibitions.servlet.model.entity.RoleType;
import my.exhibitions.servlet.model.service.ServiceFactory;
import my.exhibitions.servlet.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command{

    private static final Logger log = Logger.getLogger(RegistrationCommand.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final UserService userService = serviceFactory.createUserService();

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Execute method was invoked");

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!isUserDataValid(username, email, password)){
            request.setAttribute("fieldsRequired", "All fields required");
            log.warn("Registration data was empty");
            return "/WEB-INF/jsp/registration.jsp";
        }

        if (userExists(username)){
            request.setAttribute("duplicatedUsername", "User with entered username already in use!");
            log.warn(String.format("User with login %s already exists", username));
            return "/WEB-INF/jsp/registration.jsp";
        }

        if (!isEmailValid(email)){
            request.setAttribute("wrongEmailFormat", "Wrong email format");
            log.warn("Email was invalid");
            return "/WEB-INF/jsp/registration.jsp";
        }
        UserDTO userDTO = new UserDTO.Builder()
                .username(username)
                .password(password)
                .role(RoleType.CUSTOMER)
                .email(email)
                .build();
        userService.create(userDTO);
        return "/WEB-INF/jsp/login.jsp";
    }

    private boolean userExists(String username){
        return userService.findUserByUsername(username).isPresent();
    }

    private boolean isEmailValid(String email){
        return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    }

    private boolean isUserDataValid(String username, String email, String password){
        return username != null && !username.isEmpty() && email != null
                && !email.isEmpty() && password != null && !password.isEmpty();
    }
}
