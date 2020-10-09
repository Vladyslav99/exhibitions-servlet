package my.exhibitions.servlet.controller.command;

import my.exhibitions.servlet.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CommandUtility {

    public static void setUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }

    public static void logoutUser(HttpSession session) {
        ServletContext context = session.getServletContext();
        User user = (User) session.getAttribute("user");
        session.removeAttribute("user");
        List<User> loggedUsers = (List<User>) context.getAttribute("loggedUsers");
        loggedUsers.remove(user);
    }

    public static boolean isUserLogged(HttpServletRequest request, User user) {
        ServletContext context = request.getSession().getServletContext();
        List<User> loggedUsers = (List<User>) context.getAttribute("loggedUsers");
        boolean isUserLogged = loggedUsers.stream()
                .anyMatch(loggedUser -> loggedUser.getUsername().equals(user.getUsername()));
        if (!isUserLogged){
            loggedUsers.add(user);
            return false;
        }
        return true;
    }
}
