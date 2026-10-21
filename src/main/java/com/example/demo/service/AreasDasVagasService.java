package com.example.demo.service;

import com.example.demo.repository.AreasDasVagasRepository;
import org.springframework.stereotype.Service;

@Service
public class AreasDasVagasService {

    private final AreasDasVagasRepository repository;

    public AreasDasVagasService(AreasDasVagasRepository repository){
        this.repository = repository;
    }



}
