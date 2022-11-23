package com.example.ticketbookingapp.model.DTO;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservationWriteDTO {
    private String name;
    private String surname;
    private String dateOfBooking;
    private String tickets;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(String dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public LocalDateTime getDateAsLocalDateTime() {
        String stringDate = this.dateOfBooking;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = null;
        try {
            dateTime = LocalDateTime.parse(stringDate, formatter);
        } catch (DateTimeException ignored) {

        }

        return dateTime;
    }
}
