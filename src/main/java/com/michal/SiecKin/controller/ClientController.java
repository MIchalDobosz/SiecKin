package com.michal.SiecKin.controller;

import com.michal.SiecKin.entity.Client;
import com.michal.SiecKin.form.ClientForm;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientController {

    private ClientService clientService;


    @Autowired
    public ClientController(ClientService clientService) { this.clientService = clientService; }


    @RequestMapping("/client_list")
    @PreAuthorize("hasRole('ADMIN')")
    public String listAllClients(Model model) {

        Filter filter = new Filter();
        model.addAttribute("filter", filter);
        model.addAttribute("clientList", clientService.filterClients(filter));
        return "clientList";
    }

    @RequestMapping(value = "client_list", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String filterClient(Filter filter, Model model) {

        model.addAttribute("clientList", clientService.filterClients(filter));
        return "clientList";
    }

    @RequestMapping("/new_client")
    @PreAuthorize("hasRole('ADMIN')")
    public String newClient(Model model) {

        model.addAttribute("clientForm", new ClientForm());
        return "clientForm";
    }

    @RequestMapping(value = "/new_client", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String newClient(ClientForm clientForm) {

        clientService.save(clientService.clientFormToNewClient(clientForm));
        return "redirect:/client_list";
    }

    @RequestMapping("delete_client/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteClient(@PathVariable String id) {

        clientService.deleteById(Long.parseLong(id));
        return "redirect:/client_list";
    }

    @RequestMapping("edit_client/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editClient(@PathVariable String id, Model model) {

        model.addAttribute("clientForm", clientService.clientToClientForm(clientService.findById(Long.parseLong(id)).get()));
        return "clientForm";
    }

    @RequestMapping(value = "edit_client/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String editClient(ClientForm clientForm, Client client) {

        clientService.clientFormToExistingClient(client, clientForm);
        clientService.save(client);
        return "redirect:/client_list";
    }

}
