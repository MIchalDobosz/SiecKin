package com.michal.SiecKin.service;

import com.michal.SiecKin.entity.Movie;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.form.MovieForm;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> findAll();

    Optional<Movie> findById(Long id);

    List<Movie> filterMovies(Filter filter);

    void save(Movie movie);

    void deleteById(Long id);

    Movie movieFormToNewMovie(MovieForm movieForm);

    MovieForm movieToMovieForm(Movie movie);

    void movieFormToExistingMovie(Movie movie, MovieForm movieForm);

    List<Movie> findCurrentlyPlayedMovies(Long cinemaId);


}
