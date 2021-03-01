package com.michal.SiecKin.repository;

import com.michal.SiecKin.entity.Client;
import com.michal.SiecKin.entity.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT movie FROM Movie movie WHERE movie.title = :title")
    List<Movie> findByTitle(@Param("title") String title, Sort sort);

    @Query("SELECT movie FROM Movie movie WHERE movie.director = :director")
    List<Movie> findByDirector(@Param("director") String director, Sort sort);

    @Query("SELECT movie FROM Movie movie WHERE movie.releaseDate = :releaseDate")
    List<Movie> findByReleaseDate(@Param("releaseDate") Date releaseDate, Sort sort);

    @Query("SELECT movie FROM Movie movie WHERE movie.genre = :genre")
    List<Movie> findByGenre(@Param("genre") String genre, Sort sort);

    @Query("SELECT movie FROM Movie movie WHERE movie.runtime = :runtime")
    List<Movie> findByRuntime(@Param("runtime") Time runtime, Sort sort);

}
