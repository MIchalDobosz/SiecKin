package com.michal.SiecKin.service;


import com.michal.SiecKin.entity.TicketType;

import java.util.List;
import java.util.Optional;

public interface TicketTypeService {

    List<TicketType> findAll();

    Optional<TicketType> findById(Long id);
}
