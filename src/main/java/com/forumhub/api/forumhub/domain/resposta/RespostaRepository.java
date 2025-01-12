package com.forumhub.api.forumhub.domain.resposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    @Query("""
            select r
            from Resposta r
            where r.topico.id = :topicoId
            """)
    List<RespostaDetalhamentoDTO> findAllByTopicoId(@Param("topicoId") Long topicoId);
}
