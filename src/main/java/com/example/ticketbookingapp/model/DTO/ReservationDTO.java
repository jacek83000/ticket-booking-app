package com.example.ticketbookingapp.model.DTO;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservationDTO {

    private double totalAmountToPay;
    private String expirationDate;

    public double getTotalAmountToPay() {
        return totalAmountToPay;
    }

    public void setTotalAmountToPay(double totalAmountToPay) {
        this.totalAmountToPay = totalAmountToPay;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setAndFormatExpirationDate(LocalDateTime expirationDateLDT){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            this.expirationDate = expirationDateLDT.format(formatter);
        } catch (DateTimeException ignored) {

        }
    }
}
