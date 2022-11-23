package com.example.ticketbookingapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Integer> {

    List<Screening> findByScreeningTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

}
