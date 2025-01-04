package com.forumhub.api.forumhub.domain.topico;

import java.time.LocalDateTime;

public record TopicoDetalhamentoDTO(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        TopicoStatus status,
        String autor,
        String curso) {
    public TopicoDetalhamentoDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor().getNome(), topico.getCurso().getNome());
    }
}
