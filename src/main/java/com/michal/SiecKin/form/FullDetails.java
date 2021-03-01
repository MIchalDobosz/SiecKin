package com.michal.SiecKin.form;

public class FullDetails {

    private ClientForm clientForm;
    private UserForm userForm;


    public FullDetails(ClientForm clientForm, UserForm userForm) {
        this.clientForm = clientForm;
        this.userForm = userForm;
    }


    public ClientForm getClientForm() {
        return clientForm;
    }

    public void setClientForm(ClientForm clientForm) {
        this.clientForm = clientForm;
    }

    public UserForm getUserForm() {
        return userForm;
    }

    public void setUserForm(UserForm userForm) {
        this.userForm = userForm;
    }
}
