package my.exhibitions.servlet.model.service.impl;

import my.exhibitions.servlet.dto.HallDTO;
import my.exhibitions.servlet.model.dao.DaoFactory;
import my.exhibitions.servlet.model.dao.HallDao;
import my.exhibitions.servlet.model.entity.Hall;
import my.exhibitions.servlet.model.service.HallService;
import my.exhibitions.servlet.util.Pageable;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCHallService implements HallService {
    private static final Logger log = Logger.getLogger(JDBCHallService.class);

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public void create(HallDTO dto) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public Optional<Hall> findById(Long id) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public List<Hall> findAll() {
        List<Hall> halls = new ArrayList<>();
        try(HallDao hallDao = daoFactory.createHallDao()) {
            return hallDao.findAll();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return halls;
    }

    @Override
    public Pageable<Hall> findAll(Integer pageId, Integer total) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public void update(Long hallId) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }
}
