package my.exhibitions.servlet.model.entity;

import java.util.Objects;

public class User {

    private Long id;

    private String username;

    private String email;

    private String password;

    private Role role;

    public Long getId() {
        return id;
    }

    public User() {
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

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || this.getClass() != object.getClass()){
            return false;
        }

        User user = (User) object;
        return username.equals(user.username)
                && email.equals(user.getEmail())
                && password.equals(user.getPassword())
                && role.equals(user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, role);
    }

    public static class Builder{

        private final User user;

        public Builder() {
            user = new User();
        }

        public Builder id(Long id){
            user.id = id;
            return this;
        }

        public Builder username(String username){
            user.username = username;
            return this;
        }

        public Builder email(String email){
            user.email = email;
            return this;
        }

        public Builder password(String password){
            user.password = password;
            return this;
        }

        public Builder role(Role role){
            user.role = role;
            return this;
        }

        public User build(){
            return user;
        }
    }
}
