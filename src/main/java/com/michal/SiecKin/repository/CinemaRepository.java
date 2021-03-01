package com.michal.SiecKin.repository;
import com.michal.SiecKin.entity.Cinema;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {


    @Query("SELECT cinema FROM Cinema cinema WHERE cinema.city = :city")
    List<Cinema> findByCity(@Param("city") String city, Sort sort);

    @Query("SELECT cinema FROM Cinema cinema WHERE cinema.address = :address")
    List<Cinema> findByAddress(@Param("address") String address, Sort sort);

}
