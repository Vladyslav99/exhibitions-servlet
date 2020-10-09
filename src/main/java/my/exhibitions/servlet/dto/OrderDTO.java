package my.exhibitions.servlet.dto;

public class OrderDTO {

    private Long userId;

    private Long exhibitionEventId;

    public Long getUserId() {
        return userId;
    }

    public Long getExhibitionEventId() {
        return exhibitionEventId;
    }

    public static class Builder{
        private final OrderDTO orderDTO;

        public Builder() {
            orderDTO = new OrderDTO();
        }

        public Builder userId(Long userId){
            orderDTO.userId = userId;
            return this;
        }

        public Builder exhibitionEventId(Long exhibitionEventId){
            orderDTO.exhibitionEventId = exhibitionEventId;
            return this;
        }

        public OrderDTO build(){
            return orderDTO;
        }
    }
}
