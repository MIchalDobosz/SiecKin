package com.michal.SiecKin.form;

public class UserForm {

    private String id;
    private String username;
    private String password;
    private String role;


    public UserForm() {
        this.id = "";
        this.username = "";
        this.password = "";
        this.role = "";
    }

    public UserForm(String id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
