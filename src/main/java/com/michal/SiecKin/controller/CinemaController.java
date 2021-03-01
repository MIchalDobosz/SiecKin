package com.michal.SiecKin.controller;

import com.michal.SiecKin.entity.Cinema;
import com.michal.SiecKin.form.CinemaForm;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CinemaController {

    private CinemaService cinemaService;


    @Autowired
    public CinemaController(CinemaService cinemaService) { this.cinemaService = cinemaService; }


    @RequestMapping("/cinema_list")
    @PreAuthorize("hasRole('ADMIN')")
    public String listAllCinemas(Model model) {

        Filter filter = new Filter();
        model.addAttribute("filter", filter);
        model.addAttribute("cinemaList", cinemaService.filterCinemas(filter));
        return "cinemaList";
    }

    @RequestMapping(value = "cinema_list", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String listAllCinemas(Filter filter, Model model) {

        model.addAttribute("cinemaList", cinemaService.filterCinemas(filter));
        return "cinemaList";
    }

    @RequestMapping("/new_cinema")
    @PreAuthorize("hasRole('ADMIN')")
    public String newCinema(Model model) {

        model.addAttribute("cinemaForm", new CinemaForm());
        return "cinemaForm";
    }

    @RequestMapping(value = "/new_cinema", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String newCinema(CinemaForm cinemaForm) {

        cinemaService.save(cinemaForm.toCinema());
        return "redirect:/cinema_list";
    }

    @RequestMapping("delete_cinema/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCinema(@PathVariable String id) {

        cinemaService.deleteById(Long.parseLong(id));
        return "redirect:/cinema_list";
    }

    @RequestMapping("edit_cinema/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editCinema(@PathVariable String id, Model model) {

        model.addAttribute("cinemaForm", cinemaService.cinemaToCinemaForm(cinemaService.findById(Long.parseLong(id)).get()));
        return "cinemaForm";
    }

    @RequestMapping(value = "edit_cinema/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String editCinema(CinemaForm cinemaForm, Cinema cinema) {

        cinemaService.cinemaFormToExistingCinema(cinema, cinemaForm);
        cinemaService.save(cinema);
        return "redirect:/cinema_list";
    }
}

