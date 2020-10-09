package my.exhibitions.servlet.controller.command;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request);
}
