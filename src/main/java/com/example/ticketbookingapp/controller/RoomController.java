package com.example.ticketbookingapp.controller;


import com.example.ticketbookingapp.model.Room;
import com.example.ticketbookingapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "app/rooms")
public class RoomController {

    @Autowired
    private RoomService service;

    @GetMapping
    ResponseEntity<List<Room>> readRooms() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Transactional
    ResponseEntity<Room> createRoom(@RequestBody @Valid Room toCreate) {
        Room result = service.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
}
