package com.michal.SiecKin.service;

import com.michal.SiecKin.entity.Client;
import com.michal.SiecKin.entity.Ticket;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.form.TicketForm;
import java.util.List;
import java.util.Optional;

public interface TicketService {
    
    List<Ticket> findAll();

    Optional<Ticket> findById(Long id);

    List<Ticket> findByClient(Client client);

    List<Ticket> filterTickets(Filter filter);

    void save(Ticket ticket);

    void deleteById(Long id);

    Ticket ticketFormToNewTicket(TicketForm ticketForm);

    TicketForm ticketToTicketForm(Ticket ticket);

    void ticketFormToExistingTicket(Ticket ticket, TicketForm ticketForm);

    List<Ticket> findByScreening(Long id);

    List<Integer> findEmptySeats(Long id);
}
