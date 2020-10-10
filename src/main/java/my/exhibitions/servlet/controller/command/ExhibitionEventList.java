package my.exhibitions.servlet.controller.command;

import my.exhibitions.servlet.model.entity.Exhibition;
import my.exhibitions.servlet.model.entity.ExhibitionEvent;
import my.exhibitions.servlet.model.entity.ExhibitionEventStatus;
import my.exhibitions.servlet.model.service.ExhibitionEventService;
import my.exhibitions.servlet.model.service.ExhibitionService;
import my.exhibitions.servlet.model.service.ServiceFactory;
import my.exhibitions.servlet.util.Pageable;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ExhibitionEventList implements Command {

    private static final Logger log = Logger.getLogger(ExhibitionEventList.class);

    private static final int TOTAL_ITEMS_PER_PAGE = 2;

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ExhibitionEventService exhibitionEventService = serviceFactory.createExhibitionEventService();
    private final ExhibitionService exhibitionService = serviceFactory.createExhibitionService();

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Execute method was invoked");

        String pageIdStr = request.getParameter("pageId");
        int pageId = 1;

        if (pageIdStr != null && !pageIdStr.trim().isEmpty()) {
            pageId = Integer.parseInt(pageIdStr);
        }

        Pageable<ExhibitionEvent> exhibitionEventPageable = exhibitionEventService.findAllByStatus(pageId,
                TOTAL_ITEMS_PER_PAGE, ExhibitionEventStatus.FOR_SALE);

        List<Exhibition> exhibitions = exhibitionService.findAll();
        request.setAttribute("exhibitionEventPageable", exhibitionEventPageable);
        request.setAttribute("exhibitions", exhibitions);
        request.setAttribute("currentPage", pageId);
        request.setAttribute("pageAmount", exhibitionEventPageable.getPageAmount());
        return "/WEB-INF/jsp/user/exhibition-event-list.jsp";
    }
}
