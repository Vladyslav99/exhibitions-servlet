package my.exhibitions.servlet.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command{

    private static final Logger log = Logger.getLogger(ErrorCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Execute method was invoked");
        return "/WEB-INF/jsp/error.jsp";
    }
}
