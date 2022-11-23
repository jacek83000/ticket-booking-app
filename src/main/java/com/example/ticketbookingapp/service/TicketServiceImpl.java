package com.example.ticketbookingapp.service;

import com.example.ticketbookingapp.model.Ticket;
import com.example.ticketbookingapp.model.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository repository;

    @Override
    public Optional<Ticket> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Ticket> findAll() {
        return repository.findAll();
    }

    @Override
    public Ticket save(Ticket entity) {
        return repository.save(entity);
    }
}
