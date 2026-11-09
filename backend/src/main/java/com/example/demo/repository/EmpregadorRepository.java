package com.example.demo.repository;

import com.example.demo.model.Empregador;
import com.example.demo.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpregadorRepository extends JpaRepository<Empregador, Long> {
    List<Empregador> findAllByOrderByIdDesc();



}
