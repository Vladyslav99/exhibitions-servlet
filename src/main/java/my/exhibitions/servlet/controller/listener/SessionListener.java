package my.exhibitions.servlet.controller.listener;

import my.exhibitions.servlet.controller.command.CommandUtility;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        CommandUtility.logoutUser(session);
    }
}
