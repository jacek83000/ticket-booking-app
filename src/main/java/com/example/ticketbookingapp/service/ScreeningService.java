package com.example.ticketbookingapp.service;

import com.example.ticketbookingapp.model.Screening;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScreeningService {
    Optional<Screening> findById(Integer id);

    List<Screening> findByTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    Screening save(Screening entity);
}
