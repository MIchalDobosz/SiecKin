package com.michal.SiecKin.controller;

import com.michal.SiecKin.entity.Ticket;
import com.michal.SiecKin.entity.User;
import com.michal.SiecKin.form.*;
import com.michal.SiecKin.service.*;
import com.michal.SiecKin.serviceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.security.Principal;


@Controller
public class MainController {

    CinemaService cinemaService;
    MovieService movieService;
    ScreeningService screeningService;
    TicketService ticketService;
    UserDetailsServiceImpl userDetailsService;
    ClientService clientService;
    TicketTypeService ticketTypeService;


    @Autowired
    public MainController(CinemaService cinemaService, MovieService movieService, ScreeningService screeningService, TicketService ticketService, UserDetailsServiceImpl userDetailsService, ClientService clientService, TicketTypeService ticketTypeService) {
        this.cinemaService = cinemaService;
        this.movieService = movieService;
        this.screeningService = screeningService;
        this.ticketService = ticketService;
        this.userDetailsService = userDetailsService;
        this.clientService = clientService;
        this.ticketTypeService = ticketTypeService;
    }


    @RequestMapping("/")
    @PreAuthorize("permitAll()")
    public String index(Model model, Principal principal) {

        try {
            if(userDetailsService.findByUsername(principal.getName()).getRole().equals("ADMIN")) {
                return "mainAdmin";
            } else {
                model.addAttribute("client", clientService.findByUsername(principal.getName()));
                return "mainClient";
            }
        } catch (NullPointerException e) {
            return "main";
        }
    }

    @RequestMapping("/my_tickets")
    @PreAuthorize("hasRole('CLIENT')")
    public String clientTickets(Model model, Principal principal) {

        model.addAttribute("ticketList", ticketService.findByClient(clientService.findByUsername(principal.getName())));
        return "clientTickets";
    }

    @RequestMapping("/select_cinema")
    @PreAuthorize("permitAll()")
    public String cinemaSelector(Model model) {

        Filter filter = new Filter();
        model.addAttribute("cinemaList", cinemaService.filterCinemas(filter));
        return "cinemaSelector";
    }

    @RequestMapping("cinema/{id}")
    @PreAuthorize("permitAll()")
    public String mainPage(@PathVariable String id, Model model) {

        model.addAttribute("cinema", cinemaService.findById(Long.parseLong(id)).get());
        return "mainCinemaSelected";

    }

    @RequestMapping(value = "cinema/{cinemaId}/current_screenings")
    public String listCurrentScreenings(@PathVariable String cinemaId, Model model) {

        System.out.println(cinemaId);
        model.addAttribute("screeningList", screeningService.findCurrentScreeningsForCinema(Long.parseLong(cinemaId)));
        return "currentScreeningsList";
    }

    @RequestMapping(value = "cinema/{cinemaId}/currently_played_movies")
    @PreAuthorize("permitAll()")
    public String listCurrentlyPlayedMovies(@PathVariable String cinemaId, Model model) {

        model.addAttribute("movieList", movieService.findCurrentlyPlayedMovies(Long.parseLong(cinemaId)));
        return "currentMoviesList";
    }

    @RequestMapping(value = "cinema/{cinemaId}/movie/{movieId}/current_screenings") // TODO: Dodać link na stronie
    @PreAuthorize("permitAll()")
    public String listCurrentScreeningsForSelectedMovie(@PathVariable String cinemaId, @PathVariable String movieId, Model model) {

        model.addAttribute("screeningList", screeningService.findCurrentScreeningsForCinemaAndMovie(Long.parseLong(cinemaId) ,Long.parseLong(movieId)));
        return "currentScreeningsList";
    }

    @RequestMapping("buy_ticket/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public String buyTicket(@PathVariable String id, Model model) {

        model.addAttribute("ticketForm", new TicketForm());
        model.addAttribute("emptySeats", ticketService.findEmptySeats(Long.parseLong(id)));
        model.addAttribute("ticketTypes", ticketTypeService.findAll());
        return "ticketFormTest";
    }

    @RequestMapping(value = "buy_ticket/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('CLIENT')")
    public String buyTicket(@PathVariable String id, TicketForm ticketForm, Principal principal, Model model) {

        ticketForm.setClient(String.valueOf(clientService.findByUsername(principal.getName()).getId()));
        ticketForm.setScreening(id);
        Ticket ticket = ticketService.ticketFormToNewTicket(ticketForm);
        ticketService.save(ticket);
        model.addAttribute("ticket", ticket);
        return "ticketConfirmation";
    }


    @RequestMapping("/login")
    @PreAuthorize("permitAll()")
    public String getLogin() {
        return "login";
    }


    @RequestMapping("/register")
    @PreAuthorize("permitAll()")
    public String newUser(Model model) {

        model.addAttribute("fullDetails", new FullDetails(new ClientForm(), new UserForm()));
        return "userForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @PreAuthorize("permitAll()")
    public String newUser(FullDetails fullDetails) {

        fullDetails.getUserForm().setRole("CLIENT");
        User user = userDetailsService.userFormToNewUser(fullDetails.getUserForm());
        userDetailsService.save(user);

        fullDetails.getClientForm().setUser(String.valueOf(user.getId()));
        clientService.save(clientService.clientFormToNewClientWithUser(fullDetails.getClientForm()));
        return "redirect:/login";
    }

}
