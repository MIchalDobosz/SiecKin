package com.michal.SiecKin.controller;

import com.michal.SiecKin.entity.Theater;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.form.TheaterForm;
import com.michal.SiecKin.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TheaterController {

    private TheaterService theaterService;


    @Autowired
    public TheaterController(TheaterService theaterService) { this.theaterService = theaterService; }


    @RequestMapping("/theater_list")
    @PreAuthorize("hasRole('ADMIN')")
    public String listAllTheaters(Model model) {

        Filter filter = new Filter();
        model.addAttribute("filter", filter);
        model.addAttribute("theaterList", theaterService.filterTheater(filter));
        return "theaterList";
    }

    @RequestMapping(value = "/theater_list", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String listAllTheaters(Filter filter, Model model) {

        model.addAttribute("theaterList", theaterService.filterTheater(filter));
        return "theaterList";
    }

    @RequestMapping("/new_theater")
    @PreAuthorize("hasRole('ADMIN')")
    public String newTheater(Model model) {

        model.addAttribute("theaterForm", new TheaterForm());
        return "theaterForm";
    }

    @RequestMapping(value = "/new_theater", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String newTheater(TheaterForm theaterForm) {

        theaterService.save(theaterService.theaterFormToNewTheater(theaterForm));
        return "redirect:/theater_list";
    }

    @RequestMapping("delete_theater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTheater(@PathVariable String id) {

        theaterService.deleteById(Long.parseLong(id));
        return "redirect:/theater_list";
    }

    @RequestMapping("edit_theater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editTheater(@PathVariable String id, Model model) {

        model.addAttribute("theaterForm", theaterService.theaterToTheaterForm(theaterService.findById(Long.parseLong(id)).get()));
        return "theaterForm";
    }

    @RequestMapping(value = "edit_theater/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String editTheater(TheaterForm theaterForm, Theater theater) {

        theaterService.theaterFormToExistingTheater(theater, theaterForm);
        theaterService.save(theater);
        return "redirect:/theater_list";
    }
}
