package my.exhibitions.servlet.model.service.impl;

import my.exhibitions.servlet.dto.ExhibitionDTO;
import my.exhibitions.servlet.model.dao.DaoFactory;
import my.exhibitions.servlet.model.dao.ExhibitionDao;
import my.exhibitions.servlet.model.entity.Exhibition;
import my.exhibitions.servlet.model.service.ExhibitionService;
import my.exhibitions.servlet.util.Pageable;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCExhibitionService implements ExhibitionService {

    private static final Logger log = Logger.getLogger(JDBCExhibitionService.class);

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public void create(ExhibitionDTO dto) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public Optional<Exhibition> findById(Long id) {
        return null;
    }

    @Override
    public List<Exhibition> findAll() {
        List<Exhibition> exhibitions = new ArrayList<>();
        try (ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.findAll();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return exhibitions;
    }

    @Override
    public Pageable<Exhibition> findAll(Integer pageId, Integer total) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public void update(Long exhibitionId) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }
}
