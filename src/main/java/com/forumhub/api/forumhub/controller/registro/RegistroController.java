package com.forumhub.api.forumhub.controller.registro;

import com.forumhub.api.forumhub.domain.usuario.RegistroDTO;
import com.forumhub.api.forumhub.domain.usuario.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registro")
public class RegistroController {

    private final RegistroService registroService;

    @Autowired
    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @PostMapping
    public ResponseEntity<Void> registrar(RegistroDTO registroDTO) {
        registroService.registrarUsuario(registroDTO);

        return ResponseEntity.ok().build();
    }
}
