package com.michal.SiecKin.repository;

import com.michal.SiecKin.entity.Client;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


    @Query("SELECT client FROM Client client WHERE client.firstName = :firstName")
    List<Client> findByFirstName(@Param("firstName") String firstName, Sort sort);

    @Query("SELECT client FROM Client client WHERE client.lastName = :lastName")
    List<Client> findByLastName(@Param("lastName") String lastName, Sort sort);

    @Query("SELECT client FROM Client client WHERE client.birthDate = :birthDate")
    List<Client> findByBirthDate(@Param("birthDate") Date birthDate, Sort sort);

    @Query("SELECT client FROM Client client WHERE client.emailAddress = :emailAddress")
    List<Client> findByEmailAddress(@Param("emailAddress") String emailAddress, Sort sort);

    @Query("SELECT client FROM Client client WHERE client.phoneNumber = :phoneNumber")
    List<Client> findByPhoneNumber(@Param("phoneNumber") int phoneNumber, Sort sort);

    @Query("Select client FROM Client client WHERE client.user.username = :username")
    Client findByUsername(@Param("username") String username);

}
