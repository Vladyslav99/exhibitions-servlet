package my.exhibitions.servlet.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.logoutUser(request.getSession());
        return "redirect:/app/";
    }
}
