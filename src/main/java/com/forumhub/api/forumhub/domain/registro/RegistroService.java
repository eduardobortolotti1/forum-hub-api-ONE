package com.forumhub.api.forumhub.domain.registro;

import com.forumhub.api.forumhub.domain.usuario.Usuario;
import com.forumhub.api.forumhub.domain.usuario.UsuarioRepository;
import com.forumhub.api.forumhub.domain.usuario.UsuarioStatus;
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

        try {
            verificarEmailJaRegistrado(usuario.email());
            usuarioRepository.save(new Usuario(null, usuario.nome(), usuario.email(), encodedPassword, UsuarioStatus.ATIVO));
        }
        catch (EmailJaExistenteException e) {
            throw new EmailJaExistenteException();
        }
        catch (Exception e) {
            throw new RuntimeException("Internal server error.");
        }
    }

    public void verificarEmailJaRegistrado(String email) {
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new EmailJaExistenteException();
        }
    }
}
