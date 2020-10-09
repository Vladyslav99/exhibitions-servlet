package my.exhibitions.servlet.model.dao.mapper;

import my.exhibitions.servlet.model.entity.ExhibitionEvent;
import my.exhibitions.servlet.model.entity.ExhibitionEventStatus;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class ExhibitionEventMapper implements ObjectMapper<ExhibitionEvent>{

    @Override
    public ExhibitionEvent extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new ExhibitionEvent.Builder()
                .id(resultSet.getLong("id"))
                .dateFrom(LocalDate.parse(resultSet.getString("date_from")))
                .dateTo(LocalDate.parse(resultSet.getString("date_to")))
                .timeFrom(LocalTime.parse(resultSet.getString("time_from")))
                .timeTo(LocalTime.parse(resultSet.getString("time_to")))
                .status(ExhibitionEventStatus.valueOf(resultSet.getString("event_status")))
                .ticketCost(new BigDecimal(resultSet.getString("ticket_cost")))
                .maxAvailablePlaces(resultSet.getLong("max_available_places"))
                .soldPlaces(resultSet.getLong("sold_places"))
                .build();
    }

    @Override
    public ExhibitionEvent makeUnique(Map<Long, ExhibitionEvent> cache, ExhibitionEvent exhibitionEvent) {
        cache.putIfAbsent(exhibitionEvent.getId(), exhibitionEvent);
        return cache.get(exhibitionEvent.getId());
    }
}
