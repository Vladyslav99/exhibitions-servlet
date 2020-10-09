package my.exhibitions.servlet.controller.command;

import my.exhibitions.servlet.model.entity.ExhibitionEventStatus;
import my.exhibitions.servlet.model.service.ExhibitionEventService;
import my.exhibitions.servlet.model.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminUpdateExhibitionEventStatus implements Command{

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ExhibitionEventService exhibitionEventService = serviceFactory.createExhibitionEventService();

    @Override
    public String execute(HttpServletRequest request) {

        String exhibitionEventIdStr = request.getParameter("exhibitionEventId");
        String exhibitionEventStatusStr = request.getParameter("status");

        if (exhibitionEventIdStr == null || exhibitionEventIdStr.trim().isEmpty()
                || exhibitionEventStatusStr == null || exhibitionEventStatusStr.trim().isEmpty()){
            return "/WEB-INF/jsp/admin/exhibition-event-list.jsp";
        }
        Long exhibitionEventId = Long.parseLong(exhibitionEventIdStr);
        ExhibitionEventStatus exhibitionEventStatus = ExhibitionEventStatus.valueOf(exhibitionEventStatusStr);

        exhibitionEventService.updateStatusById(exhibitionEventId, exhibitionEventStatus);
        return "redirect:/app/admin/exhibition-event-list";
    }
}
