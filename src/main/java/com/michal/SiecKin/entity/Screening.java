package com.michal.SiecKin.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "screenings")
public class Screening {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "date")
    private Timestamp date;


    public Screening() { }

    public Screening(Theater theater, Movie movie, Timestamp date) {
        this.theater = theater;
        this.movie = movie;
        this.date = date;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Movie getMovie() { return movie; }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
