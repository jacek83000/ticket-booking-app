package com.example.ticketbookingapp.controller;

import com.example.ticketbookingapp.model.*;
import com.example.ticketbookingapp.model.DTO.RoomDTO;
import com.example.ticketbookingapp.model.DTO.SeatDTO;
import com.example.ticketbookingapp.service.SeatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "app/{date}/between:{start}&{end}/screenings/{idScreening}/seats")
public class SeatController {

    @Autowired
    private SeatService service;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping (params = "!sort")
    ResponseEntity<RoomDTO> readSeats(@PathVariable int idScreening) {
        Room room = screeningRepository.findById(idScreening).map(Screening::getRoom).orElse(null);
        return ResponseEntity.ok(convertRoomToDTO(room, idScreening));
    }

    @PostMapping
    ResponseEntity<Seat> createSeat(@RequestBody @Valid Seat toCreate){
        Seat result = service.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> deleteSeat(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private RoomDTO convertRoomToDTO(Room room, int idScreen) {
        RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);

        roomDTO.setName(room.getName());

        Screening currentScreening = screeningRepository.findById(idScreen).orElse(null);
        if(currentScreening == null)
            return null;

        Set<Ticket> allTicketsWithinRoom = new HashSet<>();
        for (Reservation reservation : currentScreening.getReservations()) {
            allTicketsWithinRoom.addAll(reservation.getTickets());
        }

        Set<Seat> seats = room.getSeats();
        Set<Seat> reservedSeats = allTicketsWithinRoom.stream().map(Ticket::getSeat).collect(Collectors.toSet());
        seats.removeAll(reservedSeats);

        Comparator<SeatDTO> compareByRoomRowAndSeatNumber = Comparator
                .comparing(SeatDTO::getRoomRow)
                .thenComparing(SeatDTO::getSeatNumber);

        List<SeatDTO> notReservedSeats = seats.stream().map(this::convertSeatToDTO).sorted(compareByRoomRowAndSeatNumber).collect(Collectors.toList());

        roomDTO.setSeats(notReservedSeats);

        return roomDTO;
    }

    private SeatDTO convertSeatToDTO(Seat seat) {
        SeatDTO seatDTO = modelMapper.map(seat, SeatDTO.class);

        seatDTO.setRoomRow(seatDTO.getRoomRow());
        seatDTO.setSeatNumber(seatDTO.getSeatNumber());

        return seatDTO;
    }
}
