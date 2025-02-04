package com.forumhub.api.forumhub.controller.registro;

import com.forumhub.api.forumhub.domain.registro.RegistroDTO;
import com.forumhub.api.forumhub.domain.registro.RegistroService;
import com.forumhub.api.forumhub.domain.usuario.Usuario;
import com.forumhub.api.forumhub.domain.usuario.UsuarioRepository;
import com.forumhub.api.forumhub.infra.TokenJWT;
import com.forumhub.api.forumhub.infra.service.TokenService;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registro")
public class RegistroController {

    private final RegistroService registroService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;

    @Autowired
    public RegistroController(RegistroService registroService, AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.registroService = registroService;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenJWT> registrar(@Valid RegistroDTO registroDTO) {
        registroService.registrarUsuario(registroDTO);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registroDTO.email(), registroDTO.senha())
        );

        Usuario usuario = usuarioRepository.findByEmail(registroDTO.email()).get();
        TokenJWT tokenJWT = new TokenJWT(tokenService.gerarToken(registroDTO.email(), usuario.getId()));
        return ResponseEntity.ok(tokenJWT);
    }
}
