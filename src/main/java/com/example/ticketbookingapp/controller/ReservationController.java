package com.example.ticketbookingapp.controller;

import com.example.ticketbookingapp.model.*;
import com.example.ticketbookingapp.model.DTO.ReservationDTO;
import com.example.ticketbookingapp.model.DTO.ReservationWriteDTO;
import com.example.ticketbookingapp.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;


@RestController
@RequestMapping(value = "app/{date}/between:{start}&{end}/screenings/{idScreening}/seats/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    ResponseEntity<List<Reservation>> readReservations() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<ReservationDTO> readReservation(@PathVariable int id, @PathVariable int idScreening) {
        Reservation reservation = service.findById(id).orElse(null);
        if (reservation == null)
            return ResponseEntity.notFound().build();

        return convertReservationToDTO(reservation, idScreening);
    }

    @PostMapping
    @Transactional
    ResponseEntity<Reservation> createReservation(@PathVariable int idScreening, @RequestBody @Valid ReservationWriteDTO toCreate) {
        Screening currentScreening = screeningRepository.findById(idScreening).orElse(null);

        if (currentScreening == null)
            return ResponseEntity.notFound().build();


        Reservation reservationToCreate = convertDTOtoReservation(toCreate, currentScreening);

        if (reservationToCreate == null)
            return ResponseEntity.badRequest().build();

        if (!chosenSeatsAreCorrect(currentScreening, reservationToCreate))
            return ResponseEntity.badRequest().build();

        Reservation result = service.save(reservationToCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }


    private ResponseEntity<ReservationDTO> convertReservationToDTO(Reservation reservation, int idScreening) {
        ReservationDTO reservationDTO = modelMapper.map(reservation, ReservationDTO.class);

        double payment = reservation.getTickets().stream().map(Ticket::getPrice).mapToDouble(Double::doubleValue).sum();
        reservationDTO.setTotalAmountToPay(payment);

        Screening screening = screeningRepository.findById(idScreening).orElse(null);
        if (screening == null)
            return ResponseEntity.badRequest().build();

        Movie movie = screening.getMovie();
        if (movie == null)
            return ResponseEntity.badRequest().build();

        LocalDateTime expirationDate = screening.getScreeningTime().plusMinutes(movie.getLengthInMinutes());
        reservationDTO.setAndFormatExpirationDate(expirationDate);

        return ResponseEntity.ok(reservationDTO);
    }

    private Reservation convertDTOtoReservation(ReservationWriteDTO reservationWriteDTO, Screening currentScreening) {
        Reservation reservation = modelMapper.map(reservationWriteDTO, Reservation.class);

        reservation.setDateOfBooking(reservationWriteDTO.getDateAsLocalDateTime());
        reservation.setScreening(currentScreening);

        LocalDateTime screeningTime = currentScreening.getScreeningTime();
        LocalDateTime dateOfBooking = reservation.getDateOfBooking();

        if (!screeningTime.minusMinutes(15).isAfter(dateOfBooking))
            return null;

        String[] ticketsString = reservationWriteDTO.getTickets().split(" ");
        Map<String, String> ticketsMap = Arrays.stream(ticketsString).map(str -> str.split("-"))
                .collect(toMap(str -> str[0], str -> str[1]));

        Set<Ticket> tickets = new HashSet<>();

        for (Map.Entry<String, String> entry : ticketsMap.entrySet()) {
            Ticket ticket = new Ticket();
            ticket.setType(entry.getValue());

            switch (entry.getValue()) {
                case "dorosły" -> ticket.setPrice(25.00);
                case "student" -> ticket.setPrice(18.00);
                case "dziecko" -> ticket.setPrice(12.50);
            }

            ticket.setReservation(reservation);
            int idRoom = currentScreening.getRoom().getId();
            Seat seat = seatRepository.findBySeatNumberAndRoomId(Integer.parseInt(entry.getKey()), idRoom).orElse(null);
            ticket.setSeat(seat);

            tickets.add(ticket);
        }

        if (tickets.size() == 0)
            return null;

        reservation.setTickets(tickets);

        return reservation;
    }

    private boolean chosenSeatsAreCorrect(Screening currentScreening, Reservation reservationToCreate) {
        Set<Reservation> reservations = currentScreening.getReservations();

        Set<Ticket> allTicketsWithinRoom = new HashSet<>();
        Set<Ticket> allTicketsWithinReservation = reservationToCreate.getTickets();

        for (Reservation reservation : reservations) {
            allTicketsWithinRoom.addAll(reservation.getTickets());
        }

        boolean haveCommonElements = !Collections.disjoint(
                allTicketsWithinReservation.stream().map(Ticket::getSeat).collect(Collectors.toSet()),
                allTicketsWithinRoom.stream().map(Ticket::getSeat).collect(Collectors.toSet()));
        if (haveCommonElements)
            return false;

        allTicketsWithinRoom.addAll(allTicketsWithinReservation);

        //new - all seats that are about to be reserved
        //all - all seats that are about to be reserved + all seats that have been already reserved

        ArrayList<Seat> allReservedSeats = allTicketsWithinRoom.stream().map(Ticket::getSeat)
                .sorted(Comparator.comparing(Seat::getSeatNumber)).collect(Collectors.toCollection(ArrayList::new));
        Map<Integer, List<Seat>> roomAll = allReservedSeats.stream().collect(Collectors.groupingBy(Seat::getRoomRow));

        ArrayList<Seat> newReservedSeats = allTicketsWithinReservation.stream().map(Ticket::getSeat)
                .sorted(Comparator.comparing(Seat::getSeatNumber)).collect(Collectors.toCollection(ArrayList::new));
        Map<Integer, List<Seat>> roomNew = newReservedSeats.stream().collect(Collectors.groupingBy(Seat::getRoomRow));

        Iterator<Map.Entry<Integer, List<Seat>>> iteratorAll = roomAll.entrySet().iterator();
        Iterator<Map.Entry<Integer, List<Seat>>> iteratorNew = roomNew.entrySet().iterator();

        List<Seat> rowWithAllSeats;
        List<Seat> rowWithNewSeats;

        while (iteratorNew.hasNext()) {

            rowWithNewSeats = iteratorNew.next().getValue();
            rowWithAllSeats = iteratorAll.next().getValue();

            for (int i = 1; i < rowWithNewSeats.size(); i++) {
                Seat seat = rowWithNewSeats.get(i - 1);
                Seat nextSeat = rowWithNewSeats.get(i);

                if (seat.getSeatNumber() + 1 != nextSeat.getSeatNumber()) {
                    for (int j = 0; j < rowWithAllSeats.size(); j++) {
                        Seat seatToCheck = rowWithAllSeats.get(j);

                        if (seatToCheck == seat) {
                            j++;
                            seatToCheck = rowWithAllSeats.get(j);
                            if (seatToCheck == nextSeat)
                                return false;
                            else
                                break;
                        }
                    }
                }
            }
        }

        return true;
    }
}
