package com.michal.SiecKin.entity;

import javax.persistence.*;

@Entity
@Table(name = "cinemas")
public class Cinema {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;


    public Cinema() { }

    public Cinema(String city, String address) {
        this.city = city;
        this.address = address;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
