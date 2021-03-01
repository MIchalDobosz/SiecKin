package com.michal.SiecKin.form;


public class TicketForm {

    private String id;
    private String screening;
    private String client;
    private String ticketType;
    private String seat;
    private String saleDate;


    public TicketForm() {
        this.id = "";
        this.screening = "";
        this.client = "";
        this.ticketType = "";
        this.seat = "";
        this.saleDate = "";
    }

    public TicketForm(String id, String screening, String client, String ticketType, String seat, String saleDate) {
        this.id = id;
        this.screening = screening;
        this.client = client;
        this.seat = seat;
        this.ticketType = ticketType;
        String dateD = saleDate.substring(0, 10);
        String time = saleDate.substring(11, 19);
        this.saleDate = dateD + "T" + time;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScreening() {
        return screening;
    }

    public void setScreening(String screening) {
        this.screening = screening;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }
}
