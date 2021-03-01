package com.michal.SiecKin.form;


public class ScreeningForm {

    private String id;
    private String theater;
    private String movie;
    private String date;


    public ScreeningForm() {
        this.id = "";
        this.theater = "";
        this.movie = "";
        this.date = "";
    }

    public ScreeningForm(String id, String theater, String movie, String date) {
        this.id = id;
        this.theater = theater;
        this.movie = movie;
        String dateD = date.substring(0, 10);
        String time = date.substring(11, 19);
        this.date = dateD + "T" + time;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheater() {
        return theater;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
