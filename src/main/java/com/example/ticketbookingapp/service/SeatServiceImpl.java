package com.example.ticketbookingapp.service;

import com.example.ticketbookingapp.model.Seat;
import com.example.ticketbookingapp.model.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository repository;

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Seat> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Seat> findAll() {
        return repository.findAll();
    }

    @Override
    public Seat save(Seat entity) {
        return repository.save(entity);
    }
}
