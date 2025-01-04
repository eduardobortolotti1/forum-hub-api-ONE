package com.forumhub.api.forumhub.controller.topico;

import com.forumhub.api.forumhub.controller.topico.services.AtualizarTopicoService;
import com.forumhub.api.forumhub.controller.topico.services.CadastrarTopicoService;
import com.forumhub.api.forumhub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.data.domain.Pageable;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository repository;

    @Autowired
    CadastrarTopicoService cadastrarTopicoService;

    @Autowired
    AtualizarTopicoService atualizarTopicoService;

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<TopicoDetalhamentoDTO> detalharTopico(@PathVariable @NotNull Long id) {
        Optional<Topico> topico = repository.findById(id);
        if (topico.isEmpty()) {
            throw new TopicoNaoEncontradoException();
        }
        return ResponseEntity.ok(new TopicoDetalhamentoDTO(topico.get()));
    }

    @GetMapping
    public Page<TopicoDetalhamentoDTO> listarTopicos(Pageable pageable) {
        Page<Topico> topicos = repository.findAll(pageable);
        return topicos.map(TopicoDetalhamentoDTO::new); // Transform each Topico into TopicoDTO
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDetalhamentoDTO> cadastrarTopico(@Valid TopicoCadastroDTO dados, UriComponentsBuilder uriBuilder) {
        Topico topicoCriado = cadastrarTopicoService.validar(dados);
        repository.save(topicoCriado);
        URI location = URI.create("/topicos/" + topicoCriado.getId());

        return ResponseEntity.created(location).body(new TopicoDetalhamentoDTO(topicoCriado));
    }

    @PatchMapping("{id}")
    @Transactional
    public ResponseEntity<TopicoDetalhamentoDTO> atualizarTopico(@PathVariable Long id, @Valid TopicoAtualizarDTO dados) {
        TopicoDetalhamentoDTO topicoAtualizado = atualizarTopicoService.atualizar(id, dados);
        return ResponseEntity.ok(topicoAtualizado);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) {
        Optional<Topico> topico = repository.findById(id);
        if (topico.isEmpty()) {
            throw new TopicoNaoEncontradoException();
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}