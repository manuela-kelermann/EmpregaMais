package com.example.demo.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface Usuariorepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
