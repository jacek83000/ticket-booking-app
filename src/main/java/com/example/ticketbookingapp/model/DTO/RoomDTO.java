package com.example.ticketbookingapp.model.DTO;

import java.util.List;

public class RoomDTO {

    private String name;
    private List<SeatDTO> seats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }
}
