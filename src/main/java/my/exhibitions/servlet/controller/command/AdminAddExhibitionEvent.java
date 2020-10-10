package my.exhibitions.servlet.controller.command;

import my.exhibitions.servlet.dto.ExhibitionEventDTO;
import my.exhibitions.servlet.model.dao.exception.HallsInUse;
import my.exhibitions.servlet.model.entity.Exhibition;
import my.exhibitions.servlet.model.entity.ExhibitionEventStatus;
import my.exhibitions.servlet.model.entity.Hall;
import my.exhibitions.servlet.model.service.ExhibitionEventService;
import my.exhibitions.servlet.model.service.ExhibitionService;
import my.exhibitions.servlet.model.service.HallService;
import my.exhibitions.servlet.model.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdminAddExhibitionEvent implements Command{

    private static final Logger log = Logger.getLogger(AdminAddExhibitionEvent.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ExhibitionService exhibitionService = serviceFactory.createExhibitionService();
    private final HallService hallService = serviceFactory.createHallService();
    private final ExhibitionEventService exhibitionEventService = serviceFactory.createExhibitionEventService();

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Execute method was invoked");

        String exhibitionIdStr = request.getParameter("exhibitionId");
        String dateFromStr = request.getParameter("dateFrom");
        String dateToStr = request.getParameter("dateTo");
        String timeFromStr = request.getParameter("timeFrom");
        String timeToStr = request.getParameter("timeTo");
        String ticketCostStr = request.getParameter("ticketCost");
        String maxAvailablePlacesStr = request.getParameter("maxAvailablePlaces");
        String[] hallIdsStr = request.getParameterValues("hallsIds");

        List<Exhibition> exhibitions = exhibitionService.findAll();
        List<Hall> halls = hallService.findAll();

        request.setAttribute("exhibitions", exhibitions);
        request.setAttribute("halls", halls);

        if (exhibitionIdStr == null || dateFromStr == null || dateToStr == null || timeFromStr == null
                || timeToStr == null || ticketCostStr == null || maxAvailablePlacesStr == null || hallIdsStr == null){
            request.setAttribute("fieldsRequired", "All fields need to be filled or selected");
            log.warn("Some of parameters were null");
            return "/WEB-INF/jsp/admin/add-exhibition-event.jsp";
        }

        Long exhibitionId = Long.parseLong(exhibitionIdStr);
        LocalDate dateFrom = LocalDate.parse(dateFromStr);
        LocalDate dateTo = LocalDate.parse(dateToStr);
        LocalTime timeFrom = LocalTime.parse(timeFromStr);
        LocalTime timeTo = LocalTime.parse(timeToStr);
        BigDecimal ticketCost = new BigDecimal(ticketCostStr);
        Long maxAvailablePlaces = Long.parseLong(maxAvailablePlacesStr);
        List<Long> hallIds = Stream.of(hallIdsStr)
                .map(Long::parseLong)
                .collect(Collectors.toCollection(ArrayList::new));


        boolean isDataValid = true;

        if (!isExhibitionIdValid(exhibitionId)){
            request.setAttribute("exhibitionError", "Error with exhibition");
            log.warn("Exhibitions id is not valid");
            isDataValid = false;
        }

        if (!isDateValid(dateFrom, dateTo)){
            request.setAttribute("dateError", "Date from can't be bigger than date to or less than today");
            log.warn("Date from and date to are not valid");
            isDataValid = false;
        }

        if (!isTimeValid(timeFrom, timeTo)){
            request.setAttribute("timeError", "Date to can't be less than date from!");
            log.warn("Time from and time to are not valid");
            isDataValid = false;
        }

        if (!isTicketCostValid(ticketCost)){
            request.setAttribute("ticketCostError", "Ticket cost can't be less or equals zero!");
            log.warn("Ticket cost is not valid");
            isDataValid = false;
        }

        if (!isMaxAvailablePlacesValid(maxAvailablePlaces)){
            request.setAttribute("maxPlaceAmountError", "Max place amount can't be less or equals zero!");
            log.warn("Max available places value is not valid");
            isDataValid = false;
        }

        if (isDataValid){
            ExhibitionEventDTO exhibitionEventDTO = new ExhibitionEventDTO.Builder()
                    .dateFrom(dateFrom)
                    .dateTo(dateTo)
                    .timeFrom(timeFrom)
                    .timeTo(timeTo)
                    .maxAvailablePlaces(maxAvailablePlaces)
                    .soldPlaces(0L)
                    .status(ExhibitionEventStatus.PLANNED)
                    .ticketCost(ticketCost)
                    .exhibitionId(exhibitionId)
                    .hallsIds(hallIds)
                    .build();

            try {
                exhibitionEventService.create(exhibitionEventDTO);
            }catch (HallsInUse exception){
                exception.printStackTrace();//log this
                log.error("Max place amount can't be less or equals zero!", exception);
                request.setAttribute("hallsInUseError", "Max place amount can't be less or equals zero!");
            }
        }

        return "/WEB-INF/jsp/admin/add-exhibition-event.jsp";
    }

    private boolean isDateValid(LocalDate dateFrom, LocalDate dateTo){
        return dateTo.compareTo(dateFrom) >= 0 && dateFrom.compareTo(LocalDate.now()) >= 0;
    }

    private boolean isTimeValid(LocalTime timeFrom, LocalTime timeTo){
        return timeTo.compareTo(timeFrom) > 0;
    }

    private boolean isTicketCostValid(BigDecimal ticketCost){
        return ticketCost.compareTo(BigDecimal.ZERO) >= 0;
    }

    private boolean isMaxAvailablePlacesValid(Long maxPlaceAmount){
        return maxPlaceAmount > 0;
    }

    private boolean isExhibitionIdValid(Long exhibitionId){
        return exhibitionId >= 0;
    }

}
