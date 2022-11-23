package com.example.ticketbookingapp.controller;

import com.example.ticketbookingapp.model.Ticket;
import com.example.ticketbookingapp.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "app/tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    @GetMapping
    ResponseEntity<List<Ticket>> readTickets() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    ResponseEntity<Ticket> createTicket(@RequestBody @Valid Ticket toCreate) {
        Ticket result = service.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
}
