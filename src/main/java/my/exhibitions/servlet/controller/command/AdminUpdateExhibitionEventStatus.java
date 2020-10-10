package my.exhibitions.servlet.controller.command;

import my.exhibitions.servlet.model.entity.ExhibitionEventStatus;
import my.exhibitions.servlet.model.service.ExhibitionEventService;
import my.exhibitions.servlet.model.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AdminUpdateExhibitionEventStatus implements Command{

    private static final Logger log = Logger.getLogger(AdminUpdateExhibitionEventStatus.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ExhibitionEventService exhibitionEventService = serviceFactory.createExhibitionEventService();

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Execute method was invoked");

        String exhibitionEventIdStr = request.getParameter("exhibitionEventId");
        String exhibitionEventStatusStr = request.getParameter("status");

        if (exhibitionEventIdStr == null || exhibitionEventIdStr.trim().isEmpty()
                || exhibitionEventStatusStr == null || exhibitionEventStatusStr.trim().isEmpty()){
            log.warn("Some of parameters were null");
            return "/WEB-INF/jsp/admin/exhibition-event-list.jsp";
        }
        Long exhibitionEventId = Long.parseLong(exhibitionEventIdStr);
        ExhibitionEventStatus exhibitionEventStatus = ExhibitionEventStatus.valueOf(exhibitionEventStatusStr);

        exhibitionEventService.updateStatusById(exhibitionEventId, exhibitionEventStatus);
        return "redirect:/app/admin/exhibition-event-list";
    }
}
