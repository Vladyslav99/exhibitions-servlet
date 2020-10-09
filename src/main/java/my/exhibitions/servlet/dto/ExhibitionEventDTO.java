package my.exhibitions.servlet.dto;

import my.exhibitions.servlet.model.entity.ExhibitionEventStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ExhibitionEventDTO {

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private LocalTime timeFrom;

    private LocalTime timeTo;

    private BigDecimal ticketCost;

    private Long maxAvailablePlaces;

    private Long soldPlaces;

    private ExhibitionEventStatus status;

    private Long exhibitionId;

    private List<Long> hallsIds;

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public BigDecimal getTicketCost() {
        return ticketCost;
    }

    public Long getMaxAvailablePlaces() {
        return maxAvailablePlaces;
    }

    public Long getSoldPlaces() {
        return soldPlaces;
    }

    public ExhibitionEventStatus getStatus() {
        return status;
    }

    public Long getExhibitionId() {
        return exhibitionId;
    }

    public List<Long> getHallsIds() {
        return hallsIds;
    }

    public static class Builder {
        private final ExhibitionEventDTO exhibitionEventDTO;

        public Builder() {
            exhibitionEventDTO = new ExhibitionEventDTO();
        }

        public Builder dateFrom(LocalDate dateFrom) {
            exhibitionEventDTO.dateFrom = dateFrom;
            return this;
        }

        public Builder dateTo(LocalDate dateTo) {
            exhibitionEventDTO.dateTo = dateTo;
            return this;
        }

        public Builder timeFrom(LocalTime timeFrom) {
            exhibitionEventDTO.timeFrom = timeFrom;
            return this;
        }

        public Builder timeTo(LocalTime timeTo) {
            exhibitionEventDTO.timeTo = timeTo;
            return this;
        }

        public Builder ticketCost(BigDecimal ticketCost) {
            exhibitionEventDTO.ticketCost = ticketCost;
            return this;
        }

        public Builder maxAvailablePlaces(Long maxAvailablePlaces) {
            exhibitionEventDTO.maxAvailablePlaces = maxAvailablePlaces;
            return this;
        }

        public Builder soldPlaces(Long soldPlaces) {
            exhibitionEventDTO.soldPlaces = soldPlaces;
            return this;
        }


        public Builder status(ExhibitionEventStatus status) {
            exhibitionEventDTO.status = status;
            return this;
        }

        public Builder exhibitionId(Long exhibitionId) {
            exhibitionEventDTO.exhibitionId = exhibitionId;
            return this;
        }

        public Builder hallsIds(List<Long> hallsIds) {
            exhibitionEventDTO.hallsIds = hallsIds;
            return this;
        }

        public ExhibitionEventDTO build() {
            return exhibitionEventDTO;
        }
    }
}
