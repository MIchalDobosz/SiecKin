package com.michal.SiecKin.entity;

import javax.persistence.*;

@Entity
@Table(name = "theaters")
public class Theater {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @Column(name = "number")
    private String number;

    @Column(name = "seats")
    private int seats;


    public Theater() { }

    public Theater(Cinema cinema, String number, int seats) {
        this.cinema = cinema;
        this.number = number;
        this.seats = seats;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
