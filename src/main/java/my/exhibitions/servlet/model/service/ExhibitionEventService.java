package my.exhibitions.servlet.model.service;

import my.exhibitions.servlet.dto.ExhibitionEventDTO;
import my.exhibitions.servlet.model.dao.exception.DaoException;
import my.exhibitions.servlet.model.entity.ExhibitionEvent;
import my.exhibitions.servlet.model.entity.ExhibitionEventStatus;
import my.exhibitions.servlet.util.Pageable;

import java.util.List;

public interface ExhibitionEventService extends GenericService<ExhibitionEvent, ExhibitionEventDTO>{
//    List<ExhibitionEvent> findAllByStatus(ExhibitionEventStatus exhibitionEventStatus);

    Pageable<ExhibitionEvent> findAllByStatus(Integer pageId, Integer total, ExhibitionEventStatus status);

    void updateStatusById(Long id, ExhibitionEventStatus status);
}
