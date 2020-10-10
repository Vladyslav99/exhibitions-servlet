package my.exhibitions.servlet.model.service.impl;

import my.exhibitions.servlet.dto.UserDTO;
import my.exhibitions.servlet.model.dao.DaoFactory;
import my.exhibitions.servlet.model.dao.UserDao;
import my.exhibitions.servlet.model.entity.Role;
import my.exhibitions.servlet.model.entity.RoleType;
import my.exhibitions.servlet.model.entity.User;
import my.exhibitions.servlet.model.service.UserService;
import my.exhibitions.servlet.util.Pageable;
import my.exhibitions.servlet.util.PasswordEncodingUtil;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class JDBCUserService implements UserService {

    private static final Logger log = Logger.getLogger(JDBCUserService.class);

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        Optional<User> userOptional = Optional.empty();
        try(UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findUserByUsernameAndPassword(username, password);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return userOptional;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        Optional<User> userOptional = Optional.empty();
        try(UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findUserByUsername(username);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return userOptional;
    }

    @Override
    public void create(UserDTO userDTO) {
        User user = new User.Builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(PasswordEncodingUtil.encode(userDTO.getPassword()))
                .role(new Role.Builder().id(2L).roleType(RoleType.CUSTOMER).build())
                .build();

        try(UserDao userDao = daoFactory.createUserDao()) {
            userDao.create(user);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public Pageable<User> findAll(Integer pageId, Integer total) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public void update(Long UserId) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }
}
