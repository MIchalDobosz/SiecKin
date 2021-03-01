package com.michal.SiecKin.repository;

import com.michal.SiecKin.entity.Screening;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Query("SELECT screening FROM Screening screening WHERE screening.date = :date")
    List<Screening> findByDate(@Param("date") Timestamp date, Sort sort);

    @Query("SELECT screening FROM Screening screening WHERE screening.movie.id = :movie")
    List<Screening> findByMovie(@Param("movie") Long movie, Sort sort);

    @Query("SELECT screening FROM Screening screening WHERE screening.theater.id = :theater")
    List<Screening> findByTheater(@Param("theater") Long theater, Sort sort);

    @Query("SELECT screening FROM Screening screening WHERE screening.date > :currentDate")
    List<Screening> findGreaterThanCurrentDate(@Param("currentDate") Timestamp currentDate);

    @Query("SELECT screening FROM Screening screening WHERE screening.date > :currentDate AND screening.theater.cinema.id = :cinema" )
    List<Screening> findGreaterThanCurrentDateAndCinema(@Param("currentDate") Timestamp currentDate, @Param("cinema") Long cinema);

    @Query("SELECT screening FROM Screening screening WHERE screening.date > :currentDate AND screening.theater.cinema.id = :cinema AND screening.movie.id = :movie")
    List<Screening> findGreaterThanCurrentDateAndCinemaAndMovie(@Param("currentDate") Timestamp currentDate, @Param("cinema") Long cinema, @Param("movie") Long movie);
}
