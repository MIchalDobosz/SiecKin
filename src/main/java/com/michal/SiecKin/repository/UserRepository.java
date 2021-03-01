package com.michal.SiecKin.repository;

import com.michal.SiecKin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);

}
