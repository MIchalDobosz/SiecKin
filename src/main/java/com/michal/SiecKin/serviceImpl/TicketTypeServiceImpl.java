package com.michal.SiecKin.serviceImpl;

import com.michal.SiecKin.entity.TicketType;
import com.michal.SiecKin.repository.TicketTypeRepository;
import com.michal.SiecKin.service.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketTypeServiceImpl implements TicketTypeService {

    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    public TicketTypeServiceImpl(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }


    @Override
    public List<TicketType> findAll() {
        return ticketTypeRepository.findAll();
    }

    @Override
    public Optional<TicketType> findById(Long id) {
        return ticketTypeRepository.findById(id);
    }
}
