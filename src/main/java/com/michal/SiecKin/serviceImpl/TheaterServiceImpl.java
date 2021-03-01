package com.michal.SiecKin.serviceImpl;

import com.michal.SiecKin.entity.Theater;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.form.TheaterForm;
import com.michal.SiecKin.repository.TheaterRepository;
import com.michal.SiecKin.service.CinemaService;
import com.michal.SiecKin.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterServiceImpl implements TheaterService {

    private TheaterRepository theaterRepository;
    private CinemaService cinemaService;


    @Autowired
    public TheaterServiceImpl(TheaterRepository theaterRepository, CinemaService cinemaService) {
        this.theaterRepository = theaterRepository;
        this.cinemaService = cinemaService;
    }


    @Override
    public List<Theater> findAll() {
        return theaterRepository.findAll();
    }

    @Override
    public Optional<Theater> findById(Long id) {
        return theaterRepository.findById(id);
    }

    @Override
    public List<Theater> filterTheater(Filter filter) {

        if(filter.getValue().equals("") && filter.getSort().equals("")) { return theaterRepository.findAll(Sort.by(Sort.Direction.ASC, "cinema")); }

        if(filter.getValue().equals("")) { return theaterRepository.findAll(Sort.by(Sort.Direction.ASC, filter.getSort())); }

        switch(filter.getType()) {
            case "cinema": return theaterRepository.findByCinema(Long.parseLong(filter.getValue()), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "number": return theaterRepository.findByNumber(filter.getValue(), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "seats": return theaterRepository.findBySeats(filter.getValue(), Sort.by(Sort.Direction.ASC, filter.getSort()));
            default: return theaterRepository.findAll();
        }
    }

    @Override
    public void save(Theater theater) {
        theaterRepository.save(theater);
    }

    @Override
    public void deleteById(Long id) {
        theaterRepository.deleteById(id);
    }

    @Override
    public Theater theaterFormToNewTheater(TheaterForm theaterForm) {
        return new Theater(cinemaService.findById(Long.parseLong(theaterForm.getCinema())).get(), theaterForm.getNumber(), Integer.parseInt(theaterForm.getSeats()));
    }

    @Override
    public TheaterForm theaterToTheaterForm(Theater theater) {
        return new TheaterForm(String.valueOf(theater.getId()) ,String.valueOf(theater.getCinema().getId()), String.valueOf(theater.getNumber()), String.valueOf(theater.getSeats()));
    }

    @Override
    public void theaterFormToExistingTheater(Theater theater, TheaterForm theaterForm) {

        theater.setCinema(cinemaService.findById(Long.parseLong(theaterForm.getCinema())).get());
        theater.setNumber(theaterForm.getNumber());
        theater.setSeats(Integer.parseInt(theaterForm.getSeats()));
    }
}
