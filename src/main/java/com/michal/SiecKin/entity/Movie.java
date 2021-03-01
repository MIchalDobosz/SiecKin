package com.michal.SiecKin.entity;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "director")
    private String director;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "genre")
    private String genre;

    @Column(name = "runtime")
    private Time runtime;


    public Movie() { }

    public Movie(String title, String director, Date releaseDate, String genre, Time runtime) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.runtime = runtime;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Time getRuntime() {
        return runtime;
    }

    public void setRuntime(Time runtime) {
        this.runtime = runtime;
    }

}
