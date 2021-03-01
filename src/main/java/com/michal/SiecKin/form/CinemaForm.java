package com.michal.SiecKin.form;

import com.michal.SiecKin.entity.Cinema;


public class CinemaForm {

    private String id;
    private String city;
    private String address;


    public CinemaForm() {
        this.id = "";
        this.city = "";
        this.address = "";
    }

    public CinemaForm(String id, String city, String address) {
        this.id = id;
        this.city = city;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Cinema toCinema() {
        return new Cinema(this.city, this.address);
    }
}
