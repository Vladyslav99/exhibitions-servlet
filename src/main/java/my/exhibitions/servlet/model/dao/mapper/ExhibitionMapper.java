package my.exhibitions.servlet.model.dao.mapper;

import my.exhibitions.servlet.model.entity.Exhibition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ExhibitionMapper implements ObjectMapper<Exhibition> {

    @Override
    public Exhibition extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Exhibition.Builder()
                .id(resultSet.getLong("exhibition_id"))
                .themeEnglish(resultSet.getString("exhibition_theme_english"))
                .themeUkrainian(resultSet.getString("exhibition_theme_ukrainian"))
                .descriptionEnglish(resultSet.getString("exhibition_description_english"))
                .descriptionUkrainian(resultSet.getString("exhibition_description_ukrainian"))
                .imageUrl(resultSet.getString("exhibition_image_url"))
                .build();
    }

    @Override
    public Exhibition makeUnique(Map<Long, Exhibition> cache, Exhibition exhibition) {
        cache.putIfAbsent(exhibition.getId(), exhibition);
        return cache.get(exhibition.getId());
    }
}
