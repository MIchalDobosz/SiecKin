package com.michal.SiecKin.controller;


import com.michal.SiecKin.entity.Screening;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.form.ScreeningForm;
import com.michal.SiecKin.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ScreeningController {

    private ScreeningService screeningService;


    @Autowired
    public ScreeningController(ScreeningService screeningService) { this.screeningService = screeningService; }


    @RequestMapping("/screening_list")
    @PreAuthorize("hasRole('ADMIN')")
    public String listAllScreenings(Model model) {

        Filter filter = new Filter();
        model.addAttribute("filter", filter);
        model.addAttribute("screeningList", screeningService.filterScreenings(filter));
        return "screeningList";
    }

    @RequestMapping(value = "screening_list", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String filterScreening(Filter filter, Model model) {

        model.addAttribute("screeningList", screeningService.filterScreenings(filter));
        return "screeningList";
    }

    @RequestMapping("/new_screening")
    @PreAuthorize("hasRole('ADMIN')")
    public String newScreening(Model model) {

        model.addAttribute("screeningForm", new ScreeningForm());
        return "screeningForm";
    }

    @RequestMapping(value = "/new_screening", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String newScreening(ScreeningForm screeningForm) {

        screeningService.save(screeningService.screeningFormToNewScreening(screeningForm));
        return "redirect:/screening_list";
    }

    @RequestMapping("delete_screening/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteScreening(@PathVariable String id) {

        screeningService.deleteById(Long.parseLong(id));
        return "redirect:/screening_list";
    }

    @RequestMapping("edit_screening/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editScreening(@PathVariable String id, Model model) {

        model.addAttribute("screeningForm", screeningService.screeningToScreeningForm(screeningService.findById(Long.parseLong(id)).get()));
        return "screeningForm";
    }

    @RequestMapping(value = "edit_screening/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String editScreening(@PathVariable String id, ScreeningForm screeningForm) {

        Screening screening = screeningService.findById(Long.parseLong(id)).get();
        screeningService.screeningFormToExistingScreening(screening, screeningForm);
        screeningService.save(screening);
        return "redirect:/screening_list";
    }


}
