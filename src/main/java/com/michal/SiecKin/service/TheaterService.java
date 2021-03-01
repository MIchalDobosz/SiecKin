package com.michal.SiecKin.service;

import com.michal.SiecKin.entity.Theater;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.form.TheaterForm;
import java.util.List;
import java.util.Optional;

public interface TheaterService {
    
    List<Theater> findAll();

    Optional<Theater> findById(Long id);

    List<Theater> filterTheater(Filter filter);

    void save(Theater theater);

    void deleteById(Long id);

    Theater theaterFormToNewTheater(TheaterForm theaterForm);

    TheaterForm theaterToTheaterForm(Theater theater);

    void theaterFormToExistingTheater(Theater theater, TheaterForm theaterForm);

}
