package com.example.demo.usuario.login;

import com.example.demo.usuario.permissoes.PerfiRespository;
import com.example.demo.usuario.permissoes.Perfil;
import com.example.demo.usuario.Usuario;
import com.example.demo.usuario.Usuariorepository;
import com.example.demo.usuario.dtos.AutenticadorDTO;
import com.example.demo.usuario.dtos.CriarContaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Usuariorepository usuariorepository;

    @Autowired
    private PerfiRespository perfiRespository;

    @PostMapping
    public ResponseEntity logar(@RequestBody @Valid AutenticadorDTO dto){
        var usuarioSenha = new UsernamePasswordAuthenticationToken(dto.email(),dto.senha());
        var autenticador = this.authenticationManager.authenticate(usuarioSenha);

        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/criar")
    public ResponseEntity<Void> criar(@RequestBody @Valid CriarContaDTO dto) {

        if (this.usuariorepository.findByEmail(dto.email()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Perfil perfil = perfiRespository.findById(dto.idPerfil()).get();

        String senhaEncriptada = new BCryptPasswordEncoder()
                .encode(dto.senha());

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(senhaEncriptada);
        usuario.setPerfil(perfil);

        this.usuariorepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
