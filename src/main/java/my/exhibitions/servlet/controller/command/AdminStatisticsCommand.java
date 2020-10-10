package my.exhibitions.servlet.controller.command;

import my.exhibitions.servlet.model.entity.ExhibitionEvent;
import my.exhibitions.servlet.model.entity.ExhibitionEventStatus;
import my.exhibitions.servlet.model.service.ExhibitionEventService;
import my.exhibitions.servlet.model.service.ServiceFactory;
import my.exhibitions.servlet.util.Pageable;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AdminStatisticsCommand implements Command {

    private static final Logger log = Logger.getLogger(AdminStatisticsCommand.class);

    private static final int TOTAL_ITEMS_PER_PAGE = 2;

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ExhibitionEventService exhibitionEventService = serviceFactory.createExhibitionEventService();

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Execute method was invoked");

        String pageIdStr = request.getParameter("pageId");
        int pageId = 1;

        if (pageIdStr != null && !pageIdStr.trim().isEmpty()) {
            pageId = Integer.parseInt(pageIdStr);
        }

        Pageable<ExhibitionEvent> exhibitionEventPageable =
                exhibitionEventService.findAllByStatus(pageId, TOTAL_ITEMS_PER_PAGE, ExhibitionEventStatus.PASSED);
        request.setAttribute("exhibitionEventPageable", exhibitionEventPageable);
        request.setAttribute("currentPage", pageId);
        request.setAttribute("pageAmount", exhibitionEventPageable.getPageAmount());
        return "/WEB-INF/jsp/admin/statistics.jsp";
    }
}
