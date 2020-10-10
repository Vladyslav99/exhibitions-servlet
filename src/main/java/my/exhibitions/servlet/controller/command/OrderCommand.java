package my.exhibitions.servlet.controller.command;

import my.exhibitions.servlet.dto.OrderDTO;
import my.exhibitions.servlet.model.entity.User;
import my.exhibitions.servlet.model.service.OrderService;
import my.exhibitions.servlet.model.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class OrderCommand implements Command{

    private static final Logger log = Logger.getLogger(OrderCommand.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.createOrderService();

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Execute method was invoked");

        User user = (User) request.getSession().getAttribute("user");
        Long exhibitionEventId = Long.parseLong(request.getParameter("eventId"));
        OrderDTO orderDTO = new OrderDTO.Builder()
                .userId(user.getId())
                .exhibitionEventId(exhibitionEventId)
                .build();

        orderService.create(orderDTO);
        return "/WEB-INF/jsp/user/purchase-successful.jsp";
    }
}
