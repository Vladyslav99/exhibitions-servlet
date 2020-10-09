package my.exhibitions.servlet.dto;

import my.exhibitions.servlet.model.entity.RoleType;

public class UserDTO {

    private String username;

    private String email;

    private String password;

    private RoleType role;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RoleType getRole() {
        return role;
    }

    public static class Builder{

        private UserDTO userDTO;

        public Builder() {
            userDTO = new UserDTO();
        }

        public Builder username(String username){
            userDTO.username = username;
            return this;
        }

        public Builder email(String email){
            userDTO.email = email;
            return this;
        }

        public Builder password(String password){
            userDTO.password = password;
            return this;
        }

        public Builder role(RoleType role){
            userDTO.role = role;
            return this;
        }

        public UserDTO build(){
            return userDTO;
        }
    }
}
