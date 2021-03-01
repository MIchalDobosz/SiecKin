package com.michal.SiecKin.repository;

import com.michal.SiecKin.entity.Theater;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {


    @Query("SELECT theater FROM Theater theater WHERE theater.cinema.id = :cinema")
    List<Theater> findByCinema(@Param("cinema") Long cinema, Sort sort);

    @Query("SELECT theater FROM Theater theater WHERE theater.number = :number")
    List<Theater> findByNumber(@Param("number") String number, Sort sort);

    @Query("SELECT theater FROM Theater theater WHERE theater.seats = :seats")
    List<Theater> findBySeats(@Param("seats") String seats, Sort sort);

}
