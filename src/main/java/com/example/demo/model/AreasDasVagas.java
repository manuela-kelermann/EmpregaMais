package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "areas_das_vagas")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class AreasDasVagas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_areas_das_vagas")
    private Long id;

    @Column (name = "nome_area")
    private String nomeArea;
}