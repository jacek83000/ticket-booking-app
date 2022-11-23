package com.example.ticketbookingapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app")
public class StartController {

    @GetMapping
    String getInfo() {
        return "Witaj na stronie rezerwującej siedzenia w kinie!";
    }
}
