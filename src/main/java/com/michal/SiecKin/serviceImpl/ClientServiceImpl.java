package com.michal.SiecKin.serviceImpl;

import com.michal.SiecKin.entity.Client;
import com.michal.SiecKin.form.ClientForm;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.repository.ClientRepository;
import com.michal.SiecKin.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private UserDetailsServiceImpl userDetailsService;


    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, UserDetailsServiceImpl userDetailsService) {
        this.clientRepository = clientRepository;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) { return clientRepository.findById(id); }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void deleteById(Long id) { clientRepository.deleteById(id); }

    @Override
    public List<Client> filterClients(Filter filter) {

        if(filter.getValue().equals("") && filter.getSort().equals("")) { return clientRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName")); }

        if(filter.getValue().equals("")) { return clientRepository.findAll(Sort.by(Sort.Direction.ASC, filter.getSort())); }

        switch(filter.getType()) {
            case "firstName": return clientRepository.findByFirstName(filter.getValue(), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "lastName": return clientRepository.findByLastName(filter.getValue(), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "birthDate": return clientRepository.findByBirthDate(Date.valueOf(filter.getValue()), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "emailAddress": return clientRepository.findByEmailAddress(filter.getValue(), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "phoneNumber": return clientRepository.findByPhoneNumber(Integer.parseInt(filter.getValue()), Sort.by(Sort.Direction.ASC, filter.getSort()));
            default: return clientRepository.findAll();
        }
    }

    @Override
    public Client clientFormToNewClient(ClientForm clientForm) {
        return new Client(clientForm.getFirstName(), clientForm.getLastName(), Date.valueOf(clientForm.getBirthDate()), clientForm.getEmailAddress(), Integer.parseInt(clientForm.getPhoneNumber()));
    }

    @Override
    public Client clientFormToNewClientWithUser(ClientForm clientForm) {
        //System.out.println(clientForm.getBirthDate());
        return new Client(clientForm.getFirstName(), clientForm.getLastName(), Date.valueOf(clientForm.getBirthDate()), clientForm.getEmailAddress(), Integer.parseInt(clientForm.getPhoneNumber()), userDetailsService.findById(Long.parseLong(clientForm.getUser())));
    }

    @Override
    public ClientForm clientToClientForm(Client client) {
        return new ClientForm(String.valueOf(client.getId()), client.getFirstName(), client.getLastName(), String.valueOf(client.getBirthDate()), client.getEmailAddress(), String.valueOf(client.getPhoneNumber()));
    }

    @Override
    public void clientFormToExistingClient(Client client, ClientForm clientForm) {

        client.setFirstName(clientForm.getFirstName());
        client.setLastName(clientForm.getLastName());
        client.setBirthDate(Date.valueOf(clientForm.getBirthDate()));
        client.setEmailAddress(clientForm.getEmailAddress());
        client.setPhoneNumber(Integer.parseInt(clientForm.getPhoneNumber()));
    }

    @Override
    public Client findByUsername(String username) {
        return clientRepository.findByUsername(username);
    }
}
