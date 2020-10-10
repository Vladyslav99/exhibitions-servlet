package my.exhibitions.servlet.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command{

    private static final Logger log = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Execute method was invoked");
        CommandUtility.logoutUser(request.getSession());
        return "redirect:/app/";
    }
}
