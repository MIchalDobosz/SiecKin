package com.michal.SiecKin.controller;

import com.michal.SiecKin.entity.Movie;
import com.michal.SiecKin.form.Filter;
import com.michal.SiecKin.form.MovieForm;
import com.michal.SiecKin.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MovieController {

    private MovieService movieService;


    @Autowired
    public MovieController(MovieService movieService) { this.movieService = movieService; }


    @RequestMapping("/movie_list")
    @PreAuthorize("hasRole('ADMIN')")
    public String listAllMovies(Model model) {

        Filter filter = new Filter();
        model.addAttribute("filter", filter);
        model.addAttribute("movieList", movieService.filterMovies(filter));
        return "movieList";
    }

    @RequestMapping(value = "movie_list", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')") //test
    public String filterMovie(Filter filter, Model model) {

        model.addAttribute("movieList", movieService.filterMovies(filter));
        return "movieList";
    }

    @RequestMapping("/new_movie")
    @PreAuthorize("hasRole('ADMIN')")
    public String newMovie(Model model) {

        model.addAttribute("movieForm", new MovieForm());
        return "movieForm";
    }

    @RequestMapping(value = "/new_movie", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String newMovie(MovieForm movieForm) {

        System.out.println(movieForm.getRuntime());
        movieService.save(movieService.movieFormToNewMovie(movieForm));
        return "redirect:/movie_list";
    }

    @RequestMapping("delete_movie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteMovie(@PathVariable String id) {

        movieService.deleteById(Long.parseLong(id));
        return "redirect:/movie_list";
    }

    @RequestMapping("edit_movie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editMovie(@PathVariable String id, Model model) {

        Movie movie = movieService.findById(Long.parseLong(id)).get();
        MovieForm movieForm = movieService.movieToMovieForm(movie);
        model.addAttribute("movieForm", movieForm);
        return "movieForm";
    }

    @RequestMapping(value = "edit_movie/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String editMovie(MovieForm movieForm, Movie movie) {

        movieService.movieFormToExistingMovie(movie, movieForm);
        movieService.save(movie);
        return "redirect:/movie_list";
    }
}
