package com.michal.SiecKin.service;

import com.michal.SiecKin.entity.Client;
import com.michal.SiecKin.form.ClientForm;
import com.michal.SiecKin.form.Filter;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> findAll();

    Optional<Client> findById(Long id);

    List<Client> filterClients(Filter filter);

    void save(Client client);

    void deleteById(Long id);

    Client clientFormToNewClient(ClientForm clientForm);

    Client clientFormToNewClientWithUser(ClientForm clientForm);

    ClientForm clientToClientForm(Client client);

    void clientFormToExistingClient(Client client, ClientForm clientForm);

    Client findByUsername(String username);
}
