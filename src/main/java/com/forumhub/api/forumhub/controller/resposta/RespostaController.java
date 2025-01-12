package com.forumhub.api.forumhub.controller.resposta;

import com.forumhub.api.forumhub.domain.resposta.*;
import com.forumhub.api.forumhub.domain.topico.TopicoDetalhamentoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<RespostaDetalhamentoDTO> cadastrarResposta(@Valid RespostaDTO resposta, UriComponentsBuilder uriBuilder) {
        Resposta respostaCadastrada =  respostaService.cadastrarResposta(resposta);
        URI location = URI.create("/resposta/" + respostaCadastrada.getId());


        return ResponseEntity.created(location).body(new RespostaDetalhamentoDTO(respostaCadastrada));
    }
    @PatchMapping("{id}")
    public ResponseEntity<RespostaDetalhamentoDTO> atualizarResposta(@PathVariable Long id, @Valid RespostaAtualizarDTO resposta) {
        RespostaDetalhamentoDTO respostaAtualizada = respostaService.atualizarResposta(resposta, id);

        return ResponseEntity.ok(respostaAtualizada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarResposta(@PathVariable Long id) {
        respostaService.deletarResposta(id);
        return ResponseEntity.noContent().build();
    }
}
