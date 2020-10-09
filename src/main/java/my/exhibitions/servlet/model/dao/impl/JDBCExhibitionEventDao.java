package my.exhibitions.servlet.model.dao.impl;

import my.exhibitions.servlet.model.dao.ExhibitionEventDao;
import my.exhibitions.servlet.model.dao.exception.DaoException;
import my.exhibitions.servlet.model.dao.exception.HallsInUse;
import my.exhibitions.servlet.model.dao.mapper.ExhibitionEventMapper;
import my.exhibitions.servlet.model.dao.mapper.ExhibitionMapper;
import my.exhibitions.servlet.model.dao.mapper.HallMapper;
import my.exhibitions.servlet.model.entity.Exhibition;
import my.exhibitions.servlet.model.entity.ExhibitionEvent;
import my.exhibitions.servlet.model.entity.ExhibitionEventStatus;
import my.exhibitions.servlet.model.entity.Hall;
import my.exhibitions.servlet.util.Pageable;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class JDBCExhibitionEventDao implements ExhibitionEventDao {

    private static final String FIND_ALL_EXHIBITION_EVENTS = "select exhibition_events.id, exhibition_events.date_from, exhibition_events.date_to, exhibition_events.time_from,\n" +
            "exhibition_events.time_to, exhibition_events.event_status, exhibition_events.ticket_cost, exhibition_events.max_available_places,\n" +
            "exhibition_events.sold_places,\n" +
            "exhibitions.id as exhibition_id, \n" +
            "exhibitions.theme_english as exhibition_theme_english,\n" +
            "exhibitions.theme_ukrainian as exhibition_theme_ukrainian, \n" +
            "exhibitions.description_english as exhibition_description_english,\n" +
            "exhibitions.description_ukrainian as exhibition_description_ukrainian,\n" +
            "exhibitions.image_url as exhibition_image_url,\n" +
            "halls.id as hall_id, \n" +
            "halls.name_english as hall_name_english,\n" +
            "halls.name_ukrainian as hall_name_ukrainian, \n" +
            "halls.description_english as hall_description_english,\n" +
            "halls.description_ukrainian as hall_description_ukrainian,\n" +
            "halls.image_url as hall_image_url\n" +
            "from exhibitions, exhibition_events, exhibition_events_halls, halls\n" +
            "where exhibitions.id = exhibition_events.exhibition_id\n" +
            "and exhibition_events_halls.exhibition_event_id = exhibition_events.id\n" +
            "and exhibition_events_halls.halls_id = halls.id order by exhibition_events.id";

    private static final String FIND_EXHIBITION_EVENT_BY_ID = "select exhibition_events.id, " +
            "exhibition_events.date_from, exhibition_events.date_to, exhibition_events.time_from, " +
            "exhibition_events.time_to, exhibition_events.event_status, exhibition_events.ticket_cost, " +
            "exhibition_events.max_available_places, exhibition_events.sold_places, " +
            "exhibitions.id as exhibition_id, exhibitions.theme_english as exhibition_theme_english, " +
            "exhibitions.theme_ukrainian as exhibition_theme_ukrainian, " +
            "exhibitions.description_english as exhibition_description_english, " +
            "exhibitions.description_ukrainian as exhibition_description_ukrainian, " +
            "exhibitions.image_url as exhibition_image_url, halls.id as hall_id, " +
            "halls.name_english as hall_name_english, halls.name_ukrainian as hall_name_ukrainian, " +
            "halls.description_english as hall_description_english, " +
            "halls.description_ukrainian as hall_description_ukrainian, " +
            "halls.image_url as hall_image_url " +
            "from exhibitions, exhibition_events, exhibition_events_halls, halls " +
            "where exhibitions.id = exhibition_events.exhibition_id " +
            "and exhibition_events_halls.exhibition_event_id = exhibition_events.id " +
            "and exhibition_events_halls.halls_id = halls.id and exhibition_events.id = ?";

    private static final String FIND_PAGEABLE_EXHIBITION_EVENTS = "select exhibition_events.id, " +
            "exhibition_events.date_from, exhibition_events.date_to, exhibition_events.time_from, " +
            "exhibition_events.time_to, exhibition_events.event_status, exhibition_events.ticket_cost, " +
            "exhibition_events.max_available_places, exhibition_events.sold_places, " +
            "exhibitions.id as exhibition_id, exhibitions.theme_english as exhibition_theme_english, " +
            "exhibitions.theme_ukrainian as exhibition_theme_ukrainian, " +
            "exhibitions.description_english as exhibition_description_english, " +
            "exhibitions.description_ukrainian as exhibition_description_ukrainian, " +
            "exhibitions.image_url as exhibition_image_url, halls.id as hall_id, " +
            "halls.name_english as hall_name_english, halls.name_ukrainian as hall_name_ukrainian, " +
            "halls.description_english as hall_description_english, " +
            "halls.description_ukrainian as hall_description_ukrainian, halls.image_url as hall_image_url " +
            "from exhibitions, exhibition_events, exhibition_events_halls, halls " +
            "where exhibitions.id = exhibition_events.exhibition_id\n" +
            "and exhibition_events_halls.exhibition_event_id = exhibition_events.id " +
            "and exhibition_events_halls.halls_id = halls.id order by exhibition_events.id limit ?, ?";

    private static final String FIND_PAGEABLE_EXHIBITION_EVENTS_BY_STATUS = "select exhibition_events.id, " +
            "exhibition_events.date_from, exhibition_events.date_to, exhibition_events.time_from, " +
            "exhibition_events.time_to, exhibition_events.event_status, exhibition_events.ticket_cost, " +
            "exhibition_events.max_available_places, exhibition_events.sold_places, " +
            "exhibitions.id as exhibition_id, exhibitions.theme_english as exhibition_theme_english, " +
            "exhibitions.theme_ukrainian as exhibition_theme_ukrainian, " +
            "exhibitions.description_english as exhibition_description_english, " +
            "exhibitions.description_ukrainian as exhibition_description_ukrainian, " +
            "exhibitions.image_url as exhibition_image_url, halls.id as hall_id, " +
            "halls.name_english as hall_name_english, halls.name_ukrainian as hall_name_ukrainian, " +
            "halls.description_english as hall_description_english, " +
            "halls.description_ukrainian as hall_description_ukrainian, halls.image_url as hall_image_url " +
            "from exhibitions, exhibition_events, exhibition_events_halls, halls " +
            "where exhibitions.id = exhibition_events.exhibition_id\n" +
            "and exhibition_events_halls.exhibition_event_id = exhibition_events.id " +
            "and exhibition_events_halls.halls_id = halls.id and exhibition_events.event_status = ? " +
            "order by exhibition_events.id limit ?, ?";

    private static final String COUNT_HALL_AMOUNT_FOR_EXHIBITION = "select count(exhibition_events_halls.halls_id) " +
            "as hall_amount from exhibitions, exhibition_events, exhibition_events_halls, halls " +
            "where exhibitions.id = exhibition_events.exhibition_id " +
            "and exhibition_events_halls.exhibition_event_id = exhibition_events.id " +
            "and exhibition_events_halls.halls_id = halls.id  group by exhibition_events.id " +
            "order by exhibition_events.id limit ?, ?";

    private static final String COUNT_EXHIBITION_EVENTS = "select count(exhibition_events.id) as page_amount " +
            "from exhibition_events";

    private static final String COUNT_HALL_AMOUNT_FOR_EXHIBITION_BY_STATUS = "select count(exhibition_events_halls.halls_id) " +
            "as hall_amount from exhibitions, exhibition_events, exhibition_events_halls, halls " +
            "where exhibitions.id = exhibition_events.exhibition_id " +
            "and exhibition_events_halls.exhibition_event_id = exhibition_events.id " +
            "and exhibition_events_halls.halls_id = halls.id " +
            "and exhibition_events.event_status = ? group by exhibition_events.id " +
            "order by exhibition_events.id limit ?, ?";

    private static final String COUNT_EXHIBITION_EVENTS_BY_STATUS = "select count(exhibition_events.id) as page_amount " +
            "from exhibition_events where exhibition_events.event_status = ?";

    private static final String UPDATE_EXHIBITION_EVENT = "update exhibition_events " +
            "set exhibition_events.date_from = ?, exhibition_events.date_to = ?, " +
            "exhibition_events.event_status = ?, exhibition_events.max_available_places = ?, " +
            "exhibition_events.sold_places = ?, exhibition_events.ticket_cost = ?, " +
            "exhibition_events.time_from = ?, exhibition_events.time_to = ?, " +
            "exhibition_events.exhibition_id = ? where exhibition_events.id = ?";

    private static final String INSERT_EXHIBITION_EVENT = "insert into exhibition_events " +
            "(date_from, date_to, event_status, max_available_places, sold_places, ticket_cost, time_from, " +
            "time_to, exhibition_id) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_EXHIBITION_EVENT_HALL = "insert into exhibition_events_halls " +
            "(exhibition_event_id, halls_id) values(?, ?)";

    private static final String UPDATE_EXHIBITION_EVENT_STATUS_BY_ID = "update exhibition_events " +
            "set exhibition_events.event_status = ? where exhibition_events.id = ?";

    private static final String FIND_ALL_HALLS_BY_DATE = "select exhibition_events.date_from, exhibition_events.date_to, " +
            "halls.id as hall_id, halls.name_english as hall_name_english," +
            "halls.name_ukrainian as hall_name_ukrainian, " +
            "halls.description_english as hall_description_english, " +
            "halls.description_ukrainian as hall_description_ukrainian, " +
            "halls.image_url as hall_image_url " +
            "from exhibitions, exhibition_events, exhibition_events_halls, halls " +
            "where exhibitions.id = exhibition_events.exhibition_id " +
            "and exhibition_events_halls.exhibition_event_id = exhibition_events.id " +
            "and exhibition_events_halls.halls_id = halls.id " +
            "and exhibition_events.date_to >= ? and exhibition_events.date_from <= ?";

    private final Connection connection;

    public JDBCExhibitionEventDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ExhibitionEvent exhibitionEvent) {

        try (PreparedStatement exhibitionEventStatement =
                     connection.prepareStatement(INSERT_EXHIBITION_EVENT, Statement.RETURN_GENERATED_KEYS)) {
            List<Hall> foundHalls = findAllHallsByDate(exhibitionEvent.getDateFrom(), exhibitionEvent.getDateTo());
            if (isHallsInUse(exhibitionEvent.getHalls(), foundHalls)){
                throw new HallsInUse("Selected halls are already in use");
            }
            connection.setAutoCommit(false);
            exhibitionEventStatement.setString(1, exhibitionEvent.getDateFrom().toString());
            exhibitionEventStatement.setString(2, exhibitionEvent.getDateTo().toString());
            exhibitionEventStatement.setString(3, exhibitionEvent.getStatus().name());
            exhibitionEventStatement.setLong(4, exhibitionEvent.getMaxAvailablePlaces());
            exhibitionEventStatement.setLong(5, exhibitionEvent.getSoldPlaces());
            exhibitionEventStatement.setString(6, exhibitionEvent.getTicketCost().toString());
            exhibitionEventStatement.setString(7, exhibitionEvent.getTimeFrom().toString());
            exhibitionEventStatement.setString(8, exhibitionEvent.getTimeTo().toString());
            exhibitionEventStatement.setLong(9, exhibitionEvent.getExhibition().getId());
            exhibitionEventStatement.executeUpdate();
            try (PreparedStatement exhibitionEventHallStatement =
                         connection.prepareStatement(INSERT_EXHIBITION_EVENT_HALL);
                 ResultSet resultSet = exhibitionEventStatement.getGeneratedKeys()) {
                resultSet.next();
                exhibitionEvent.setId(resultSet.getLong(1));
                for (Hall hall : exhibitionEvent.getHalls()) {
                    exhibitionEventHallStatement.setLong(1, exhibitionEvent.getId());
                    exhibitionEventHallStatement.setLong(2, hall.getId());
                    exhibitionEventHallStatement.executeUpdate();
                }
            }
            connection.setAutoCommit(true);
        } catch (SQLException exception) {
            exception.printStackTrace();//log this
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DaoException(throwables);
            }
            throw new DaoException(exception);
        }
    }

    private List<Hall> findAllHallsByDate(LocalDate dateFrom, LocalDate dateTo) throws SQLException {
        List<Hall> halls = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_HALLS_BY_DATE)) {
            statement.setString(1, dateFrom.toString());
            statement.setString(2, dateTo.toString());
            ResultSet resultSet = statement.executeQuery();
            HallMapper hallMapper = new HallMapper();
            while (resultSet.next()) {
                halls.add(hallMapper.extractFromResultSet(resultSet));
            }
            return halls;
        }
    }

    private boolean isHallsInUse(List<Hall> selectedHalls, List<Hall> foundHalls){
        for (Hall selectedHall : selectedHalls) {
            for (Hall hall : foundHalls) {
                if (hall.getId().equals(selectedHall.getId())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Optional<ExhibitionEvent> findById(Long id) throws DaoException {
        Optional<ExhibitionEvent> exhibitionEventOptional = Optional.empty();
        try(PreparedStatement statement = connection.prepareStatement(FIND_EXHIBITION_EVENT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                exhibitionEventOptional = Optional.of(extract(resultSet).get(0));
            }
            return exhibitionEventOptional;
        } catch (SQLException exception) {
            exception.printStackTrace();//log this
            throw new DaoException(exception);
        }
    }

    @Override
    public List<ExhibitionEvent> findAll() throws DaoException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_EXHIBITION_EVENTS);
            return extract(resultSet);
        } catch (SQLException exception) {
            //log here
            exception.printStackTrace();
            throw new DaoException(exception);
        }
    }

    @Override
    public Pageable<ExhibitionEvent> findAll(Integer pageId, Integer total) throws DaoException {
        int currentPage = pageId - 1;
        int startPosition = 0;
        int hallsAmount = 0;
        try (PreparedStatement hallAmountStatement = connection.prepareStatement(COUNT_HALL_AMOUNT_FOR_EXHIBITION)) {
            connection.setAutoCommit(false);
            hallAmountStatement.setLong(1, 0);
            hallAmountStatement.setLong(2, currentPage * total);
            ResultSet hallAmountResultSet = hallAmountStatement.executeQuery();
            while (hallAmountResultSet.next()) {
                startPosition += hallAmountResultSet.getInt("hall_amount");
            }

            hallAmountStatement.setLong(1, currentPage * total);
            hallAmountStatement.setLong(2, total);
            hallAmountResultSet = hallAmountStatement.executeQuery();
            while (hallAmountResultSet.next()) {
                hallsAmount += hallAmountResultSet.getInt("hall_amount");
            }
            int pageAmount = 0;
            try (Statement statement = connection.createStatement()) {
                ResultSet pageAmountResultSet = statement.executeQuery(COUNT_EXHIBITION_EVENTS);
                if (pageAmountResultSet.next()) {
                    pageAmount = (int) Math.ceil(pageAmountResultSet.getDouble("page_amount") / total);
                }
            }
            try (PreparedStatement pageableEventStatement = connection.prepareStatement(FIND_PAGEABLE_EXHIBITION_EVENTS)) {
                pageableEventStatement.setLong(1, startPosition);
                pageableEventStatement.setLong(2, hallsAmount);
                ResultSet pageableResultSet = pageableEventStatement.executeQuery();
                List<ExhibitionEvent> exhibitionEvents = extract(pageableResultSet);
                connection.setAutoCommit(true);
                return new Pageable<>(exhibitionEvents, pageAmount, pageId);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();//log this
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DaoException(throwables);
            }
            throw new DaoException(exception);
        }
    }

    @Override
    public Pageable<ExhibitionEvent> findAllByStatus(Integer pageId, Integer total, ExhibitionEventStatus status)
            throws DaoException {
        int currentPage = pageId - 1;
        int startPosition = 0;
        int hallsAmount = 0;
        try (PreparedStatement hallAmountStatement =
                     connection.prepareStatement(COUNT_HALL_AMOUNT_FOR_EXHIBITION_BY_STATUS)) {
            connection.setAutoCommit(false);
            hallAmountStatement.setString(1, status.name());
            hallAmountStatement.setLong(2, 0);
            hallAmountStatement.setLong(3, currentPage * total);
            ResultSet hallAmountResultSet = hallAmountStatement.executeQuery();
            while (hallAmountResultSet.next()) {
                startPosition += hallAmountResultSet.getInt("hall_amount");
            }

            hallAmountStatement.setString(1, status.name());
            hallAmountStatement.setLong(2, currentPage * total);
            hallAmountStatement.setLong(3, total);
            hallAmountResultSet = hallAmountStatement.executeQuery();
            while (hallAmountResultSet.next()) {
                hallsAmount += hallAmountResultSet.getInt("hall_amount");
            }
            int pageAmount = 0;
            try (PreparedStatement statement = connection.prepareStatement(COUNT_EXHIBITION_EVENTS_BY_STATUS)) {
                statement.setString(1, status.name());
                ResultSet pageAmountResultSet = statement.executeQuery();
                if (pageAmountResultSet.next()) {
                    pageAmount = (int) Math.ceil(pageAmountResultSet.getDouble("page_amount") / total);
                }
            }
            try (PreparedStatement pageableEventStatement =
                         connection.prepareStatement(FIND_PAGEABLE_EXHIBITION_EVENTS_BY_STATUS)) {
                pageableEventStatement.setString(1, status.name());
                pageableEventStatement.setLong(2, startPosition);
                pageableEventStatement.setLong(3, hallsAmount);
                ResultSet pageableResultSet = pageableEventStatement.executeQuery();
                List<ExhibitionEvent> exhibitionEvents = extract(pageableResultSet);
                connection.setAutoCommit(true);
                return new Pageable<>(exhibitionEvents, pageAmount, pageId);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();//log this
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DaoException(throwables);
            }
            throw new DaoException(exception);
        }
    }

    private List<ExhibitionEvent> extract(ResultSet resultSet) throws SQLException {
        Map<Long, ExhibitionEvent> exhibitionEvents = new LinkedHashMap<>();
        Map<Long, Hall> halls = new LinkedHashMap<>();
        Map<Long, Exhibition> exhibitions = new LinkedHashMap<>();

        HallMapper hallMapper = new HallMapper();
        ExhibitionMapper exhibitionMapper = new ExhibitionMapper();
        ExhibitionEventMapper exhibitionEventMapper = new ExhibitionEventMapper();
        while (resultSet.next()) {
            Exhibition exhibition = exhibitionMapper.extractFromResultSet(resultSet);
            Hall hall = hallMapper.extractFromResultSet(resultSet);
            ExhibitionEvent exhibitionEvent = exhibitionEventMapper.extractFromResultSet(resultSet);
            exhibition = exhibitionMapper.makeUnique(exhibitions, exhibition);
            hall = hallMapper.makeUnique(halls, hall);
            exhibitionEvent = exhibitionEventMapper.makeUnique(exhibitionEvents, exhibitionEvent);
            exhibitionEvent.setExhibition(exhibition);
            exhibitionEvent.getHalls().add(hall);
        }
        return new ArrayList<>(exhibitionEvents.values());
    }


    @Override
    public void update(ExhibitionEvent exhibitionEvent) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_EXHIBITION_EVENT)) {
            statement.setString(1, exhibitionEvent.getDateFrom().toString());
            statement.setString(2, exhibitionEvent.getDateTo().toString());
            statement.setString(3, exhibitionEvent.getStatus().name());
            statement.setLong(4, exhibitionEvent.getMaxAvailablePlaces());
            statement.setLong(5, exhibitionEvent.getSoldPlaces());
            statement.setString(6, exhibitionEvent.getTicketCost().toString());
            statement.setString(7, exhibitionEvent.getTimeFrom().toString());
            statement.setString(8, exhibitionEvent.getTimeTo().toString());
            statement.setLong(9, exhibitionEvent.getExhibition().getId());
            statement.setLong(10, exhibitionEvent.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();//log this
            throw new DaoException(exception);
        }
    }


    @Override
    public void updateStatusById(Long exhibitionEventId, ExhibitionEventStatus status) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_EXHIBITION_EVENT_STATUS_BY_ID)) {
            statement.setString(1, status.name());
            statement.setLong(2, exhibitionEventId);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();//log this
            throw new DaoException(exception);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {

    }


    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }
}
