package com.example.ticketbookingapp.service;

import com.example.ticketbookingapp.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    Optional<Ticket> findById(Integer id);

    List<Ticket> findAll();

    Ticket save(Ticket entity);
}
