package com.michal.SiecKin.controller;

import com.michal.SiecKin.entity.Screening;
import com.michal.SiecKin.entity.Ticket;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.form.ScreeningForm;
import com.michal.SiecKin.form.TheaterForm;
import com.michal.SiecKin.form.TicketForm;
import com.michal.SiecKin.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TicketController {

    private TicketService ticketService;


    @Autowired
    public TicketController(TicketService ticketService) { this.ticketService = ticketService; }


    @RequestMapping("/ticket_list")
    @PreAuthorize("hasRole('ADMIN')")
    public String listAllTickets(Model model) {

        Filter filter = new Filter();
        model.addAttribute("filter", filter);
        model.addAttribute("ticketList", ticketService.filterTickets(filter));
        return "ticketList";
    }

    @RequestMapping(value = "ticket_list", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String filterTicket(Filter filter, Model model) {

        model.addAttribute("ticketList", ticketService.filterTickets(filter));
        return "ticketList";
    }

    @RequestMapping("/new_ticket")
    @PreAuthorize("hasRole('ADMIN')")
    public String newTicket(Model model) {

        model.addAttribute("ticketForm", new TicketForm());
        return "ticketForm";
    }

    @RequestMapping(value = "/new_ticket", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String newTicket(TicketForm ticketForm) {

        ticketService.save(ticketService.ticketFormToNewTicket(ticketForm));
        return "redirect:/ticket_list";
    }

    @RequestMapping("delete_ticket/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTicket(@PathVariable String id) {

        ticketService.deleteById(Long.parseLong(id));
        return "redirect:/ticket_list";
    }

    @RequestMapping("edit_ticket/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editTicket(@PathVariable String id, Model model) {

        model.addAttribute("ticketForm", ticketService.ticketToTicketForm(ticketService.findById(Long.parseLong(id)).get()));
        return "ticketForm";
    }

    @RequestMapping(value = "edit_ticket/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String editTicket(@PathVariable String id, TicketForm ticketForm) {

        Ticket ticket = ticketService.findById(Long.parseLong(id)).get();
        ticketService.ticketFormToExistingTicket(ticket, ticketForm);
        ticketService.save(ticket);
        return "redirect:/ticket_list";
    }

}
