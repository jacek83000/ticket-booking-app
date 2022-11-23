package com.example.ticketbookingapp.service;

import com.example.ticketbookingapp.model.Reservation;
import com.example.ticketbookingapp.model.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Override
    public Optional<Reservation> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Reservation> findAll() {
        return repository.findAll();
    }

    @Override
    public Reservation save(Reservation entity) {
        return repository.save(entity);
    }
}
