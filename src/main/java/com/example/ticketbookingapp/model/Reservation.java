package com.example.ticketbookingapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank(message = "Imię nie może być puste.")
    @Pattern(regexp = "^\\p{Lu}\\p{Ll}{2,}$", message = "Imię musi zaczynać się z dużej litery i składać się z minimum 3 liter.")
    private String name;

    @NotNull
    @NotBlank(message = "Nazwisko nie może być puste.")
    @Pattern(regexp = "^\\p{Lu}\\p{Ll}{2,}(-\\p{Lu}\\p{Ll}{2,})?$", message = "Nazwisko musi zaczynać się z dużej litery " +
            "i składać się z minimum 3 liter. Naziwsko dwuczłonowe musi być oddzielone znakiem '-'.")
    private String surname;

    private LocalDateTime dateOfBooking;

    @ManyToOne
    @JoinColumn(name = "screening_id", referencedColumnName = "id")
    private Screening screening;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;

    public Reservation() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDateTime getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(LocalDateTime dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
