package com.forumhub.api.forumhub.domain.usuario;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public RegistroService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void registrarUsuario(@Valid RegistroDTO usuario) {
        String encodedPassword = encoder.encode(usuario.senha());

        usuarioRepository.save(new Usuario(null, usuario.nome(), usuario.email(), encodedPassword, UsuarioStatus.ATIVO));
    }
}
