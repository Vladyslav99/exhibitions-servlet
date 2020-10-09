package my.exhibitions.servlet.model.entity;

public class Order {

    private Long id;

    private User user;

    private ExhibitionEvent exhibitionEvent;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public ExhibitionEvent getExhibitionEvent() {
        return exhibitionEvent;
    }

    public static class Builder {
        private final Order order;

        public Builder() {
            order = new Order();
        }

        public Builder id(Long id){
            order.id = id;
            return this;
        }

        public Builder user(User user){
            order.user = user;
            return this;
        }

        public Builder exhibitionEvent(ExhibitionEvent exhibitionEvent){
            order.exhibitionEvent = exhibitionEvent;
            return this;
        }

        public Order build(){
            return order;
        }
    }
}
