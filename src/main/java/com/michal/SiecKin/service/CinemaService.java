package com.michal.SiecKin.service;

import com.michal.SiecKin.entity.Cinema;
import com.michal.SiecKin.form.CinemaForm;
import com.michal.SiecKin.form.Filter;

import java.util.List;
import java.util.Optional;

public interface CinemaService {

    List<Cinema> findAll();

    Optional<Cinema> findById(Long id);

    List<Cinema> filterCinemas(Filter filter);

    void save(Cinema cinema);

    void deleteById(Long id);

    Cinema cinemaFormToNewCinema(CinemaForm cinemaForm);

    CinemaForm cinemaToCinemaForm(Cinema cinema);

    void cinemaFormToExistingCinema(Cinema cinema, CinemaForm cinemaForm);
}
