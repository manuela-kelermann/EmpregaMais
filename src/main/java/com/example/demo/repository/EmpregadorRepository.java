package com.example.demo.repository;

import com.example.demo.model.Empregador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpregadorRepository extends JpaRepository<Empregador, Long> {


}
