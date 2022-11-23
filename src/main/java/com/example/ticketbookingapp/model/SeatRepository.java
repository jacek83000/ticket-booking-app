package com.example.ticketbookingapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    Optional<Seat> findBySeatNumberAndRoomId(Integer seatNumber, Integer roomId);
}
