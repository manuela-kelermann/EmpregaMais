package com.example.demo.repository;

import com.example.demo.model.ModalidadeEnum;
import com.example.demo.model.StatusEnum;
import com.example.demo.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

    List<Vaga> findAllByOrderByIdDesc();

    Optional<Vaga> findByTituloIgnoreCase(String titulo);

    List<Vaga> findByTituloVagaContainingIgnoreCase(String titulo);

    List<Vaga> findByLocalidade(String localidade);

    List<Vaga> findByModalidade(ModalidadeEnum modalidadeEnum);

    List<Vaga> findByStatus(StatusEnum statusEnum);






}
