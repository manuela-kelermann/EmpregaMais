package com.example.demo.usuario.login;

import com.example.demo.usuario.Usuariorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutorizadorService implements UserDetailsService {

    @Autowired
    Usuariorepository usuariorepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuariorepository.findByEmail(email);
    }
}
