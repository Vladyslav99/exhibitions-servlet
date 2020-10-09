package my.exhibitions.servlet.model.entity;

public class Role {

    private Long id;

    private RoleType roleType;

    public Long getId() {
        return id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public static class Builder{
        private final Role role;

        public Builder() {
            role = new Role();
        }

        public Builder id(Long id){
            role.id = id;
            return this;
        }

        public Builder roleType(RoleType roleType){
            role.roleType = roleType;
            return this;
        }

        public Role build(){
            return role;
        }
    }
}
