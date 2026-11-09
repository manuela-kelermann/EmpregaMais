package com.example.demo.repository;

import com.example.demo.model.AreasDasVagas;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AreasDasVagasRepository extends JpaRepository<AreasDasVagas, Long> {

    Optional<AreasDasVagas> findByNomeAreaIgnoreCase(String nomeArea);

    List<AreasDasVagas> findByNomeAreaContainingIgnoreCase(String nomeArea);
}
