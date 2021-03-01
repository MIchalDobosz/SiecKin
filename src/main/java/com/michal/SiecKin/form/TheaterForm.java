package com.michal.SiecKin.form;

public class TheaterForm {

    private String id;
    private String cinema;
    private String number;
    private String seats;


    public TheaterForm() {
        this.id = "";
        this.cinema = "";
        this.number = "";
        this.seats = "";
    }

    public TheaterForm(String id, String cinema, String number, String seats) {
        this.id = id;
        this.cinema = cinema;
        this.number = number;
        this.seats = seats;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
