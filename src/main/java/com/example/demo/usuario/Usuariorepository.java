package com.example.demo.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


@Repository
public interface Usuariorepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);
}
