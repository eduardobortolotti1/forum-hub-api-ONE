package com.forumhub.api.forumhub.domain.resposta;

import java.time.LocalDateTime;

public record RespostaDetalhamentoDTO(
        String mensagem,
        LocalDateTime data_criacao,
        String nome_autor,
        Long id_autor,
        Boolean solucao
) {
    public RespostaDetalhamentoDTO(Resposta resposta) {
        this(
                resposta.getMensagem(),
                resposta.getDataCriacao(),
                resposta.getAutor().getNome(),
                resposta.getAutor().getId(),
                resposta.getSolucao()
        );
    }
}
