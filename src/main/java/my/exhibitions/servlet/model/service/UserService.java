package my.exhibitions.servlet.model.service;

import my.exhibitions.servlet.dto.UserDTO;
import my.exhibitions.servlet.model.entity.User;

import java.util.Optional;

public interface UserService extends GenericService<User, UserDTO> {
    Optional<User> findUserByUsernameAndPassword(String username, String password);

    Optional<User> findUserByUsername(String username);
}
