package my.exhibitions.servlet.model.dao.mapper;

import my.exhibitions.servlet.model.entity.Hall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class HallMapper implements ObjectMapper<Hall>{

    @Override
    public Hall extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Hall.Builder()
                .id(resultSet.getLong("hall_id"))
                .nameEnglish(resultSet.getString("hall_name_english"))
                .nameUkrainian(resultSet.getString("hall_name_ukrainian"))
                .descriptionEnglish(resultSet.getString("hall_description_english"))
                .descriptionUkrainian(resultSet.getString("hall_description_ukrainian"))
                .imageUrl(resultSet.getString("hall_image_url"))
                .build();
    }

    @Override
    public Hall makeUnique(Map<Long, Hall> cache, Hall hall) {
        cache.putIfAbsent(hall.getId(), hall);
        return cache.get(hall.getId());
    }
}
