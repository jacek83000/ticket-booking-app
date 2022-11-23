package com.example.ticketbookingapp.service;

import com.example.ticketbookingapp.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Optional<Room> findById(Integer id);

    List<Room> findAll();

    Room save(Room entity);
}
