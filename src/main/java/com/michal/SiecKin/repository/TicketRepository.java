package com.michal.SiecKin.repository;

import com.michal.SiecKin.entity.Client;
import com.michal.SiecKin.entity.Ticket;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT ticket FROM Ticket ticket WHERE ticket.screening.id = :screening")
    List<Ticket> findByScreening(@Param("screening") Long screening);

    @Query("SELECT ticket FROM Ticket ticket WHERE ticket.screening.id = :screening")
    List<Ticket> findByScreening(@Param("screening") Long screening, Sort sort);

    @Query("SELECT ticket FROM Ticket ticket WHERE ticket.client.id = :client")
    List<Ticket> findByClient(@Param("client") Long client, Sort sort);

    @Query("SELECT ticket FROM Ticket ticket WHERE ticket.seat = :seat")
    List<Ticket> findBySeat(@Param("seat") int seat, Sort sort);

    @Query("SELECT ticket FROM Ticket ticket WHERE ticket.saleDate = :saleDate")
    List<Ticket> findBySaleDate(@Param("saleDate") Timestamp saleDate, Sort sort);

    List<Ticket> findByClient(Client client);
}
