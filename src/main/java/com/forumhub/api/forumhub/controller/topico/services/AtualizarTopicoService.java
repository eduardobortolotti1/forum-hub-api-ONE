package com.forumhub.api.forumhub.controller.topico.services;

import com.forumhub.api.forumhub.domain.topico.*;
import com.forumhub.api.forumhub.infra.security.OwnerValidators.TopicoOwnerValidation;
import com.forumhub.api.forumhub.infra.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtualizarTopicoService {
    private final TopicoRepository repository;
    private final TopicoOwnerValidation ownerValidator;

    @Autowired
    public AtualizarTopicoService(TopicoRepository repository, TopicoOwnerValidation validator) {
        this.repository = repository;
        this.ownerValidator = validator;
    }

    public TopicoDetalhamentoDTO atualizar(Long id, @Valid TopicoAtualizarDTO dados) {
        Optional<Topico> topicoOriginal = repository.findById(id);

        if (topicoOriginal.isEmpty()) {
            throw new TopicoNaoEncontradoException();
        }

        ownerValidator.validate(topicoOriginal.get());

        topicoOriginal.get().setTitulo(dados.titulo());
        topicoOriginal.get().setMensagem(dados.mensagem());

        repository.save(topicoOriginal.get());
        return new TopicoDetalhamentoDTO(topicoOriginal.get());
    }
}
