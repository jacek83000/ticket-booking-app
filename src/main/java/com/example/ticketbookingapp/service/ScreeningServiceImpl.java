package com.example.ticketbookingapp.service;

import com.example.ticketbookingapp.model.Screening;
import com.example.ticketbookingapp.model.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    @Autowired
    private ScreeningRepository repository;

    @Override
    public Optional<Screening> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Screening> findByTimeBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByScreeningTimeBetween(startDate, endDate);
    }

    @Override
    public Screening save(Screening entity) {
        return repository.save(entity);
    }
}
