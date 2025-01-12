package com.forumhub.api.forumhub.controller.login;

import com.forumhub.api.forumhub.domain.login.LoginRequestDTO;
import com.forumhub.api.forumhub.domain.usuario.Usuario;
import com.forumhub.api.forumhub.domain.usuario.UsuarioRepository;
import com.forumhub.api.forumhub.infra.TokenJWT;
import com.forumhub.api.forumhub.infra.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<TokenJWT> logar(@Valid LoginRequestDTO loginRequestDTO, HttpServletRequest httpServletRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.senha())
        );

        Usuario usuario = usuarioRepository.findByEmail(loginRequestDTO.email()).get();
        TokenJWT tokenJWT = new TokenJWT(tokenService.gerarToken(loginRequestDTO.email(), usuario.getId()));
        return ResponseEntity.ok(tokenJWT);
    }
}
