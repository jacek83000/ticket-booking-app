package com.example.ticketbookingapp.service;

import com.example.ticketbookingapp.model.Room;
import com.example.ticketbookingapp.model.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository repository;

    @Override
    public Optional<Room> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Room> findAll() {
        return repository.findAll();
    }

    @Override
    public Room save(Room entity) {
        return repository.save(entity);
    }
}
