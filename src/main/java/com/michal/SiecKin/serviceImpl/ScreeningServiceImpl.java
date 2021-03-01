package com.michal.SiecKin.serviceImpl;

import com.michal.SiecKin.entity.Screening;
import com.michal.SiecKin.entity.Ticket;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.form.ScreeningForm;
import com.michal.SiecKin.repository.ScreeningRepository;
import com.michal.SiecKin.service.MovieService;
import com.michal.SiecKin.service.ScreeningService;
import com.michal.SiecKin.service.TheaterService;
import com.michal.SiecKin.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private ScreeningRepository screeningRepository;
    private TheaterService theaterService;
    private MovieService movieService;
    private TicketService ticketService;


    @Autowired
    public ScreeningServiceImpl(ScreeningRepository screeningRepository, TheaterService theaterService, MovieService movieService, @Lazy TicketService ticketService) {
        this.screeningRepository = screeningRepository;
        this.theaterService = theaterService;
        this.movieService = movieService;
        this.ticketService = ticketService;
    }


    @Override
    public List<Screening> findAll() {
        return screeningRepository.findAll();
    }

    @Override
    public Optional<Screening> findById(Long id) { return screeningRepository.findById(id); }

    @Override
    public void save(Screening screening) { screeningRepository.save(screening); }

    @Override
    public void deleteById(Long id) { screeningRepository.deleteById(id); }

    @Override
    public List<Screening> filterScreenings(Filter filter) {

        if(filter.getValue().equals("") && filter.getSort().equals("")) { return screeningRepository.findAll(Sort.by(Sort.Direction.ASC, "date")); }

        if(filter.getValue().equals("")) { return screeningRepository.findAll(Sort.by(Sort.Direction.ASC, filter.getSort())); }

        switch(filter.getType()) {
            case "date": return screeningRepository.findByDate(Timestamp.valueOf(filter.getValue()), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "theater": return screeningRepository.findByTheater(Long.parseLong(filter.getValue()), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "movie": return screeningRepository.findByMovie(Long.parseLong(filter.getValue()), Sort.by(Sort.Direction.ASC, filter.getSort()));
            default: return screeningRepository.findAll();
        }
    }

    @Override
    public Screening screeningFormToNewScreening(ScreeningForm screeningForm) {

        String date = screeningForm.getDate().substring(0, 10);
        String time = screeningForm.getDate().substring(11, 19);
        String dateTime = date + " " + time;
        return new Screening(theaterService.findById(Long.parseLong(screeningForm.getTheater())).get(), movieService.findById(Long.parseLong(screeningForm.getMovie())).get(), Timestamp.valueOf(dateTime));
    }

    @Override
    public ScreeningForm screeningToScreeningForm(Screening screening) {
        return new ScreeningForm(String.valueOf(screening.getId()), String.valueOf(screening.getTheater().getId()), String.valueOf(screening.getMovie().getId()), String.valueOf(screening.getDate()));
    }

    @Override
    public void screeningFormToExistingScreening(Screening screening, ScreeningForm screeningForm) {

        String date = screeningForm.getDate().substring(0, 10);
        String time = screeningForm.getDate().substring(11, 19);
        String dateTime = date + " " + time;
        System.out.println(dateTime);
        screening.setTheater(theaterService.findById(Long.parseLong(screeningForm.getTheater())).get());
        screening.setMovie(movieService.findById(Long.parseLong(screeningForm.getMovie())).get());
        screening.setDate(Timestamp.valueOf(dateTime));

    }

    @Override
    public List<Screening> findCurrentScreenings() {
        return screeningRepository.findGreaterThanCurrentDate(new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public List<Screening> findCurrentScreeningsForCinema(Long cinemaId) {
        return screeningRepository.findGreaterThanCurrentDateAndCinema(new Timestamp(System.currentTimeMillis()), cinemaId);
    }

    @Override
    public List<Screening> findCurrentScreeningsForCinemaAndMovie(Long cinemaId, Long movieId) {
        return screeningRepository.findGreaterThanCurrentDateAndCinemaAndMovie(new Timestamp(System.currentTimeMillis()), cinemaId, movieId);
    }
}
