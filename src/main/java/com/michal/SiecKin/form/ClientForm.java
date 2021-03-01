package com.michal.SiecKin.form;

import com.michal.SiecKin.entity.Client;
import java.sql.Date;


public class ClientForm {

    private String id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String emailAddress;
    private String phoneNumber;
    private String user;


    public ClientForm() {
        this.id = "";
        this.firstName = "";
        this.lastName = "";
        this.birthDate = "";
        this.emailAddress = "";
        this.phoneNumber = "";
        this.user = "";
    }

    public ClientForm(String id, String firstName, String lastName, String birthDate, String emailAddress, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
