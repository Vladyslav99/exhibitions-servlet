package my.exhibitions.servlet.model.dao;

import my.exhibitions.servlet.model.dao.exception.DaoException;
import my.exhibitions.servlet.model.entity.ExhibitionEvent;
import my.exhibitions.servlet.model.entity.ExhibitionEventStatus;
import my.exhibitions.servlet.util.Pageable;

public interface ExhibitionEventDao extends GenericDao<ExhibitionEvent> {

    Pageable<ExhibitionEvent> findAllByStatus(Integer pageId, Integer total, ExhibitionEventStatus status) throws DaoException;

    void updateStatusById(Long exhibitionEventId, ExhibitionEventStatus status);
}
