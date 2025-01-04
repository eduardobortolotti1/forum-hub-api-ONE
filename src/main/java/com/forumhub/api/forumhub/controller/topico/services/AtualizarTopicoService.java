package com.forumhub.api.forumhub.controller.topico.services;

import com.forumhub.api.forumhub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtualizarTopicoService {
    private final TopicoRepository repository;

    @Autowired
    public AtualizarTopicoService(TopicoRepository repository) {
        this.repository = repository;
    }

    public TopicoDetalhamentoDTO atualizar(Long id, @Valid TopicoAtualizarDTO dados) {
        Optional<Topico> topicoOriginal = repository.findById(id);

        if (topicoOriginal.isEmpty()) {
            throw new TopicoNaoEncontradoException();
        }

        topicoOriginal.get().setTitulo(dados.titulo());
        topicoOriginal.get().setMensagem(dados.mensagem());

        repository.save(topicoOriginal.get());
        return new TopicoDetalhamentoDTO(topicoOriginal.get());
    }
}
