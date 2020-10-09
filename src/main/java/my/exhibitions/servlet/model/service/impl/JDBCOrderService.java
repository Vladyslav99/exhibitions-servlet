package my.exhibitions.servlet.model.service.impl;

import my.exhibitions.servlet.dto.OrderDTO;
import my.exhibitions.servlet.model.dao.DaoFactory;
import my.exhibitions.servlet.model.dao.OrderDao;
import my.exhibitions.servlet.model.entity.ExhibitionEvent;
import my.exhibitions.servlet.model.entity.Order;
import my.exhibitions.servlet.model.entity.User;
import my.exhibitions.servlet.model.service.OrderService;
import my.exhibitions.servlet.util.Pageable;

import java.util.List;
import java.util.Optional;

public class JDBCOrderService implements OrderService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public void create(OrderDTO orderDTO) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            User user = new User.Builder()
                    .id(orderDTO.getUserId())
                    .build();
            ExhibitionEvent exhibitionEvent = new ExhibitionEvent.Builder()
                    .id(orderDTO.getExhibitionEventId())
                    .build();

            Order order = new Order.Builder()
                    .user(user)
                    .exhibitionEvent(exhibitionEvent)
                    .build();
            orderDao.create(order);
        } catch (Exception exception) {
            exception.printStackTrace();//log this
            //rethrow service exception
        }
    }

    @Override
    public Optional<Order> findById(Long id) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public List<Order> findAll() {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public Pageable<Order> findAll(Integer pageId, Integer total) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public void update(Long entityId) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }
}
