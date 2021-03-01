package com.michal.SiecKin.serviceImpl;

import com.michal.SiecKin.entity.Cinema;
import com.michal.SiecKin.form.CinemaForm;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.repository.CinemaRepository;
import com.michal.SiecKin.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaServiceImpl implements CinemaService {

    private CinemaRepository cinemaRepository;


    @Autowired
    public CinemaServiceImpl(CinemaRepository cinemaRepository) { this.cinemaRepository = cinemaRepository; }


    @Override
    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    @Override
    public Optional<Cinema> findById(Long id) {
        return cinemaRepository.findById(id);
    }

    @Override
    public void save(Cinema cinema) { cinemaRepository.save(cinema); }

    @Override
    public void deleteById(Long id) {
        cinemaRepository.deleteById(id);
    }

    @Override
    public List<Cinema> filterCinemas(Filter filter) {

        if(filter.getValue().equals("") && filter.getSort().equals("")) { return cinemaRepository.findAll(Sort.by(Sort.Direction.ASC, "city")); }

        if(filter.getValue().equals("")) { return cinemaRepository.findAll(Sort.by(Sort.Direction.ASC, filter.getSort())); }

        switch(filter.getType()) {
            case "city": return cinemaRepository.findByCity(filter.getValue(), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "address": return cinemaRepository.findByAddress(filter.getValue(), Sort.by(Sort.Direction.ASC, filter.getSort()));
            default: return cinemaRepository.findAll();
        }
    }

    @Override
    public Cinema cinemaFormToNewCinema(CinemaForm cinemaForm) {
        return new Cinema(cinemaForm.getCity(), cinemaForm.getAddress());
    }

    @Override
    public CinemaForm cinemaToCinemaForm(Cinema cinema) {
        return new CinemaForm(String.valueOf(cinema.getId()), cinema.getCity(), cinema.getAddress());
    }

    @Override
    public void cinemaFormToExistingCinema(Cinema cinema, CinemaForm cinemaForm) {

        cinema.setCity(cinemaForm.getCity());
        cinema.setAddress(cinemaForm.getAddress());
    }
}
