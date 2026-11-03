package com.example.demo.usuario.login;

import com.example.demo.usuario.permissoes.PerfiRespository;
import com.example.demo.usuario.permissoes.Perfil;
import com.example.demo.usuario.Usuario;
import com.example.demo.usuario.Usuariorepository;
import com.example.demo.usuario.dtos.AutenticadorDTO;
import com.example.demo.usuario.dtos.CriarSalvarUsuarioDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest; // 👈 OBRIGATÓRIO para a sessão funcionar
import jakarta.servlet.http.HttpSession;      // 👈 OBRIGATÓRIO para a sessão funcionar
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder; // 👈 OBRIGATÓRIO
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
    public ResponseEntity<Void> logar(@RequestBody @Valid AutenticadorDTO dto, HttpServletRequest request) {

        var usuarioSenha = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var autenticador = this.authenticationManager.authenticate(usuarioSenha);

        SecurityContextHolder.getContext().setAuthentication(autenticador);

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/criar")
    public ResponseEntity<Void> criar(@RequestBody @Valid CriarSalvarUsuarioDTO dto) {

        if (this.usuariorepository.findByEmail(dto.email()).isPresent()) {
            throw new EntityExistsException("Já existe uma conta com o email: " + dto.email());
        }

        Perfil perfil = perfiRespository.findById(dto.idPerfil())
                .orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado"));

        String senhaEncriptada = new BCryptPasswordEncoder().encode(dto.senha());

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(senhaEncriptada);
        usuario.setPerfil(perfil);

        this.usuariorepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editar(@PathVariable Long id, @RequestBody @Valid CriarSalvarUsuarioDTO dto) {

        Usuario usuario = usuariorepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuariorepository.findByEmail(dto.email())
                .ifPresent(usuarioComEmail -> {
                    if (!usuarioComEmail.getId().equals(id)) {
                        throw new EntityExistsException("Já existe uma conta com o email: " + dto.email());
                    }
                });

        Perfil perfil = perfiRespository.findById(dto.idPerfil())
                .orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado"));

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setPerfil(perfil);

        if (dto.senha() != null && !dto.senha().isBlank()) {
            usuario.setSenha(new BCryptPasswordEncoder().encode(dto.senha()));
        }

        usuariorepository.save(usuario);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        Usuario usuario = usuariorepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuariorepository.delete(usuario);

        return ResponseEntity.noContent().build();
    }
}
