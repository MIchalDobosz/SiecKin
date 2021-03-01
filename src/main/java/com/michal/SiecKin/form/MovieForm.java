package com.michal.SiecKin.form;


public class MovieForm {

    private String id;
    private String title;
    private String director;
    private String releaseDate;
    private String genre;
    private String runtime;


    public MovieForm() {
        this.id = "";
        this.title = "";
        this.director = "";
        this.releaseDate = "";
        this.genre = "";
        this.runtime = "";
    }

    public MovieForm(String id, String title, String director, String releaseDate, String genre, String runtime) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.runtime = runtime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
}
