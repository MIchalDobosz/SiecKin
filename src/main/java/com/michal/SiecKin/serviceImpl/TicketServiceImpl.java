package com.michal.SiecKin.serviceImpl;

import com.michal.SiecKin.entity.*;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.form.TicketForm;
import com.michal.SiecKin.repository.TicketRepository;
import com.michal.SiecKin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private ScreeningService screeningService;
    private ClientService clientService;
    private TicketTypeService ticketTypeService;


    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, ScreeningService screeningService, ClientService clientService, TicketTypeService ticketTypeService) {
        this.ticketRepository = ticketRepository;
        this.screeningService = screeningService;
        this.clientService = clientService;
        this.ticketTypeService = ticketTypeService;
    }


    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> findById(Long id) { return ticketRepository.findById(id); }

    @Override
    public List<Ticket> findByClient(Client client) {
        return ticketRepository.findByClient(client);
    }

    @Override
    public void save(Ticket ticket) { ticketRepository.save(ticket); }

    @Override
    public void deleteById(Long id) { ticketRepository.deleteById(id); }

    @Override
    public List<Ticket> filterTickets(Filter filter) {
        if(filter.getValue().equals("") && filter.getSort().equals("")) { return ticketRepository.findAll(Sort.by(Sort.Direction.ASC, "saleDate")); }

        if(filter.getValue().equals("")) { return ticketRepository.findAll(Sort.by(Sort.Direction.ASC, filter.getSort())); }

        switch(filter.getType()) {
            case "screening": return ticketRepository.findByScreening(Long.parseLong(filter.getValue()), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "client": return ticketRepository.findByClient(Long.parseLong(filter.getValue()), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "seat": return ticketRepository.findBySeat(Integer.parseInt(filter.getValue()), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "saleDate": return ticketRepository.findBySaleDate(Timestamp.valueOf(filter.getValue()), Sort.by(Sort.Direction.ASC, filter.getSort()));
            default: return ticketRepository.findAll();
        }
    }

    @Override
    public Ticket ticketFormToNewTicket(TicketForm ticketForm) {
        return new Ticket(screeningService.findById(Long.parseLong(ticketForm.getScreening())).get(), clientService.findById(Long.parseLong(ticketForm.getClient())).get(), ticketTypeService.findById(Long.parseLong(ticketForm.getTicketType())).get(), Integer.parseInt(ticketForm.getSeat()), new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public TicketForm ticketToTicketForm(Ticket ticket) {
        return new TicketForm(String.valueOf(ticket.getId()), String.valueOf(ticket.getScreening().getId()), String.valueOf(ticket.getClient().getId()), String.valueOf(ticket.getTicketType()), String.valueOf(ticket.getSeat()), String.valueOf(ticket.getSaleDate()));
    }

    @Override
    public void ticketFormToExistingTicket(Ticket ticket, TicketForm ticketForm) {
        String date = ticketForm.getSaleDate().substring(0, 10);
        String time = ticketForm.getSaleDate().substring(11, 19);
        String dateTime = date + " " + time;
        ticket.setScreening(screeningService.findById(Long.parseLong(ticketForm.getScreening())).get());
        ticket.setClient(clientService.findById(Long.parseLong(ticketForm.getClient())).get());
        ticket.setTicketType(ticketTypeService.findById(Long.parseLong(ticketForm.getTicketType())).get());
        ticket.setSeat(Integer.parseInt(ticketForm.getSeat()));
        ticket.setSaleDate(Timestamp.valueOf(dateTime));
    }

    @Override
    public List<Ticket> findByScreening(Long screening) {
        return ticketRepository.findByScreening(screening);
    }

    @Override
    public List<Integer> findEmptySeats(Long screening) {
        Screening screeningObj = screeningService.findById(screening).get();
        List<Ticket> ticketList = ticketRepository.findByScreening(screening);
        List<Integer> takenSeatsList = new ArrayList<>();
        List<Integer> emptySeatsList = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            takenSeatsList.add(ticket.getSeat());
        }

        for(int i = 1; i <= screeningObj.getTheater().getSeats(); i++) {

            if (!takenSeatsList.contains(i)) {
                emptySeatsList.add(i);
            }
        }

        return emptySeatsList;
    }
}
