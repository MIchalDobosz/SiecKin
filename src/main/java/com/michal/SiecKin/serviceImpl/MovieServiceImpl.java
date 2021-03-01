package com.michal.SiecKin.serviceImpl;

import com.michal.SiecKin.entity.Movie;
import com.michal.SiecKin.entity.Screening;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.form.MovieForm;
import com.michal.SiecKin.repository.MovieRepository;
import com.michal.SiecKin.service.MovieService;
import com.michal.SiecKin.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private ScreeningService screeningService;


    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, @Lazy ScreeningService screeningService) {
        this.movieRepository = movieRepository;
        this.screeningService = screeningService;
    }


    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public void save(Movie movie) { movieRepository.save(movie); }

    @Override
    public void deleteById(Long id) { movieRepository.deleteById(id); }

    @Override
    public List<Movie> filterMovies(Filter filter) {
        if(filter.getValue().equals("") && filter.getSort().equals("")) { return movieRepository.findAll(Sort.by(Sort.Direction.ASC, "title")); }

        if(filter.getValue().equals("")) { return movieRepository.findAll(Sort.by(Sort.Direction.ASC, filter.getSort())); }

        switch(filter.getType()) {
            case "title": return movieRepository.findByTitle(filter.getValue(), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "director": return movieRepository.findByDirector(filter.getValue(), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "releaseDate": return movieRepository.findByReleaseDate(Date.valueOf(filter.getValue()), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "genre": return movieRepository.findByGenre(filter.getValue(), Sort.by(Sort.Direction.ASC, filter.getSort()));
            case "runtime": return movieRepository.findByRuntime(Time.valueOf(filter.getValue()), Sort.by(Sort.Direction.ASC, filter.getSort()));
            default: return movieRepository.findAll();
        }
    }

    @Override
    public Movie movieFormToNewMovie(MovieForm movieForm) {
        return new Movie(movieForm.getTitle(), movieForm.getDirector(), Date.valueOf(movieForm.getReleaseDate()), movieForm.getGenre(), Time.valueOf(movieForm.getRuntime() + ":00"));
    }

    @Override
    public MovieForm movieToMovieForm(Movie movie) {
        return new MovieForm(String.valueOf(movie.getId()), movie.getTitle(), movie.getDirector(), String.valueOf(movie.getReleaseDate()), movie.getGenre(), String.valueOf(movie.getRuntime()));
    }

    @Override
    public void movieFormToExistingMovie(Movie movie, MovieForm movieForm) {

        movie.setTitle(movieForm.getTitle());
        movie.setDirector(movieForm.getDirector());
        movie.setReleaseDate(Date.valueOf(movieForm.getReleaseDate()));
        movie.setGenre(movieForm.getGenre());
        movie.setRuntime(Time.valueOf(movieForm.getRuntime()));
    }

    @Override
    public List<Movie> findCurrentlyPlayedMovies(Long cinemaId) {

        List<Long> moviesId = new ArrayList<>();
        List<Screening> currentScreenings = screeningService.findCurrentScreeningsForCinema(cinemaId);
        for (Screening screening : currentScreenings) {

            if (!moviesId.contains(screening.getMovie().getId())) {
                moviesId.add(screening.getMovie().getId());
            }
        }

        List<Movie> movieList = movieRepository.findAllById(moviesId);
        System.out.println(movieList);
        return movieList;
    }
}
