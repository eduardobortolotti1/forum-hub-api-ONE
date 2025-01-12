package com.forumhub.api.forumhub.controller.resposta;

import com.forumhub.api.forumhub.domain.resposta.RespostaDTO;
import com.forumhub.api.forumhub.domain.resposta.RespostaDetalhamentoDTO;
import com.forumhub.api.forumhub.domain.resposta.RespostaNaoEncontradaException;
import com.forumhub.api.forumhub.domain.resposta.RespostaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resposta")
public class RespostaController {
    private final RespostaService respostaService;
    private final RespostaRepository respostaRepository;

    @Autowired
    public RespostaController(RespostaService respostaService, RespostaRepository respostaRepository) {
        this.respostaService = respostaService;
        this.respostaRepository = respostaRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<List<RespostaDetalhamentoDTO>> buscarRespostas(@PathVariable Long id) {
        return ResponseEntity.ok(respostaService.buscarRespostas(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<RespostaDetalhamentoDTO> cadastrarResposta(@PathVariable Long id, @Valid RespostaDTO resposta) {
        RespostaDetalhamentoDTO respostaCadastrada =  respostaService.cadastrarResposta(resposta, id);

        return ResponseEntity.ok(respostaCadastrada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarResposta(@PathVariable Long id) {
        respostaService.deletarResposta(id);
        return ResponseEntity.noContent().build();
    }
}
