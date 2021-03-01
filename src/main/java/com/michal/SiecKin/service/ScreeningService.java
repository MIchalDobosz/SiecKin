package com.michal.SiecKin.service;

import com.michal.SiecKin.entity.Screening;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.form.ScreeningForm;

import java.util.List;
import java.util.Optional;

public interface ScreeningService {
    
    List<Screening> findAll();

    Optional<Screening> findById(Long id);

    List<Screening> filterScreenings(Filter filter);

    void save(Screening screening);

    void deleteById(Long id);

    Screening screeningFormToNewScreening(ScreeningForm screeningForm);

    ScreeningForm screeningToScreeningForm(Screening screening);

    void screeningFormToExistingScreening(Screening screening, ScreeningForm screeningForm);

    List<Screening> findCurrentScreenings();

    List<Screening> findCurrentScreeningsForCinema(Long cinema);

    List<Screening> findCurrentScreeningsForCinemaAndMovie(Long cinema, Long movie);

}
