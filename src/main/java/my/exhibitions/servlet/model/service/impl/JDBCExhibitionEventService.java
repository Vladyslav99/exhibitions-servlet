package my.exhibitions.servlet.model.service.impl;

import my.exhibitions.servlet.dto.ExhibitionEventDTO;
import my.exhibitions.servlet.model.dao.DaoFactory;
import my.exhibitions.servlet.model.dao.ExhibitionEventDao;
import my.exhibitions.servlet.model.dao.exception.HallsInUse;
import my.exhibitions.servlet.model.entity.Exhibition;
import my.exhibitions.servlet.model.entity.ExhibitionEvent;
import my.exhibitions.servlet.model.entity.ExhibitionEventStatus;
import my.exhibitions.servlet.model.entity.Hall;
import my.exhibitions.servlet.model.service.ExhibitionEventService;
import my.exhibitions.servlet.util.Pageable;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCExhibitionEventService implements ExhibitionEventService {

    private static final Logger log = Logger.getLogger(JDBCExhibitionEventService.class);

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public void create(ExhibitionEventDTO exhibitionEventDTO) {
        Exhibition exhibition = new Exhibition.Builder()
                .id(exhibitionEventDTO.getExhibitionId())
                .build();
        List<Hall> halls = new ArrayList<>();
        for (Long hallId : exhibitionEventDTO.getHallsIds()) {
            Hall hall = new Hall.Builder()
                    .id(hallId)
                    .build();
            halls.add(hall);
        }
        ExhibitionEvent exhibitionEvent = new ExhibitionEvent.Builder()
                .dateFrom(exhibitionEventDTO.getDateFrom())
                .dateTo(exhibitionEventDTO.getDateTo())
                .timeFrom(exhibitionEventDTO.getTimeFrom())
                .timeTo(exhibitionEventDTO.getTimeTo())
                .status(exhibitionEventDTO.getStatus())
                .maxAvailablePlaces(exhibitionEventDTO.getMaxAvailablePlaces())
                .soldPlaces(exhibitionEventDTO.getSoldPlaces())
                .ticketCost(exhibitionEventDTO.getTicketCost())
                .exhibition(exhibition)
                .halls(halls)
                .build();
        try (ExhibitionEventDao exhibitionEventDao = daoFactory.createExhibitionEventDao()) {
            exhibitionEventDao.create(exhibitionEvent);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new HallsInUse(exception);
        }
    }

    @Override
    public Optional<ExhibitionEvent> findById(Long id) {
        return null;
    }

    @Override
    public List<ExhibitionEvent> findAll() {
        List<ExhibitionEvent> exhibitionEvents = new ArrayList<>();
        try (ExhibitionEventDao exhibitionEventDao = daoFactory.createExhibitionEventDao()) {
            return exhibitionEventDao.findAll();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return exhibitionEvents;
    }

    @Override
    public Pageable<ExhibitionEvent> findAll(Integer pageId, Integer total) {
        try (ExhibitionEventDao exhibitionEventDao = daoFactory.createExhibitionEventDao()) {
            return exhibitionEventDao.findAll(pageId, total);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new RuntimeException();
        }
    }

    @Override
    public Pageable<ExhibitionEvent> findAllByStatus(Integer pageId, Integer total, ExhibitionEventStatus status) {
        try(ExhibitionEventDao exhibitionEventDao = daoFactory.createExhibitionEventDao()) {
            return exhibitionEventDao.findAllByStatus(pageId, total, status);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void updateStatusById(Long exhibitionEventId, ExhibitionEventStatus status) {
        try (ExhibitionEventDao exhibitionEventDao = daoFactory.createExhibitionEventDao()) {
            exhibitionEventDao.updateStatusById(exhibitionEventId, status);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
    }

    @Override
    public void update(Long exhibitionEventId) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

}
