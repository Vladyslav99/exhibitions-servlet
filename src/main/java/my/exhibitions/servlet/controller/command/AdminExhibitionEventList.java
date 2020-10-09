package my.exhibitions.servlet.controller.command;

import my.exhibitions.servlet.model.entity.ExhibitionEvent;
import my.exhibitions.servlet.model.service.ExhibitionEventService;
import my.exhibitions.servlet.model.service.ServiceFactory;
import my.exhibitions.servlet.util.Pageable;

import javax.servlet.http.HttpServletRequest;

public class AdminExhibitionEventList implements Command{

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ExhibitionEventService exhibitionEventService = serviceFactory.createExhibitionEventService();

    private static final int TOTAL_ITEMS_PER_PAGE = 2;

    @Override
    public String execute(HttpServletRequest request) {

        String pageIdStr = request.getParameter("pageId");
        int pageId = 1;

        if (pageIdStr != null && !pageIdStr.trim().isEmpty()){
            pageId = Integer.parseInt(pageIdStr);
        }

        Pageable<ExhibitionEvent> exhibitionEventPageable = exhibitionEventService.findAll(pageId, TOTAL_ITEMS_PER_PAGE);
        request.setAttribute("exhibitionEventPageable", exhibitionEventPageable);
        request.setAttribute("currentPage", pageId);
        request.setAttribute("pageAmount", exhibitionEventPageable.getPageAmount());
        return "/WEB-INF/jsp/admin/exhibition-event-list.jsp";
    }
}
