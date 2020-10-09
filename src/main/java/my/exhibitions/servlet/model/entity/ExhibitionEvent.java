package my.exhibitions.servlet.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ExhibitionEvent {

    private Long id;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private LocalTime timeFrom;

    private LocalTime timeTo;

    private BigDecimal ticketCost;

    private Long maxAvailablePlaces;

    private Long soldPlaces;

    private ExhibitionEventStatus status;

    private Exhibition exhibition;

    private List<Hall> halls = new ArrayList<>();

    public Long getId() {
        return id;
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public void setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
    }

    public void setTicketCost(BigDecimal ticketCost) {
        this.ticketCost = ticketCost;
    }

    public void setMaxAvailablePlaces(Long maxAvailablePlaces) {
        this.maxAvailablePlaces = maxAvailablePlaces;
    }

    public void setSoldPlaces(Long soldPlaces) {
        this.soldPlaces = soldPlaces;
    }

    public void setStatus(ExhibitionEventStatus status) {
        this.status = status;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }

    public Long getSoldPlaces() {
        return soldPlaces;
    }

    public ExhibitionEventStatus getStatus() {
        return status;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public static class Builder {
        private final ExhibitionEvent exhibitionEvent;

        public Builder() {
            exhibitionEvent = new ExhibitionEvent();
        }

        public Builder id(Long id){
            exhibitionEvent.id = id;
            return this;
        }

        public Builder dateFrom(LocalDate dateFrom){
            exhibitionEvent.dateFrom = dateFrom;
            return this;
        }

        public Builder dateTo(LocalDate dateTo){
            exhibitionEvent.dateTo = dateTo;
            return this;
        }

        public Builder timeFrom(LocalTime timeFrom){
            exhibitionEvent.timeFrom = timeFrom;
            return this;
        }

        public Builder timeTo(LocalTime timeTo){
            exhibitionEvent.timeTo = timeTo;
            return this;
        }

        public Builder ticketCost(BigDecimal ticketCost){
            exhibitionEvent.ticketCost = ticketCost;
            return this;
        }

        public Builder maxAvailablePlaces(Long maxAvailablePlaces){
            exhibitionEvent.maxAvailablePlaces = maxAvailablePlaces;
            return this;
        }

        public Builder soldPlaces(Long soldPlaces){
            exhibitionEvent.soldPlaces = soldPlaces;
            return this;
        }


        public Builder status(ExhibitionEventStatus status){
            exhibitionEvent.status = status;
            return this;
        }

        public Builder exhibition(Exhibition exhibition){
            exhibitionEvent.exhibition = exhibition;
            return this;
        }

        public Builder halls(List<Hall> halls){
            exhibitionEvent.halls = halls;
            return this;
        }

        public ExhibitionEvent build(){
            return exhibitionEvent;
        }
    }

}
