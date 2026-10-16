package com.example.demo.usuario.permissoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfiRespository extends JpaRepository<Perfil, Long> {

}
