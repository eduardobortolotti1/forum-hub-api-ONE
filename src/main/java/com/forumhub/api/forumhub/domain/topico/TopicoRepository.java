package com.forumhub.api.forumhub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Optional<List<Topico>> findByTituloAndMensagem(String titulo, String mensagem);
}
