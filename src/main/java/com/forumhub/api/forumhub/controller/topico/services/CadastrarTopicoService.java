package com.forumhub.api.forumhub.controller.topico.services;

import com.forumhub.api.forumhub.domain.curso.Curso;
import com.forumhub.api.forumhub.domain.curso.CursoNaoEncontradoException;
import com.forumhub.api.forumhub.domain.curso.CursoRepository;
import com.forumhub.api.forumhub.domain.topico.TopicoCadastroDTO;
import com.forumhub.api.forumhub.domain.topico.Topico;
import com.forumhub.api.forumhub.domain.topico.TopicoRepository;
import com.forumhub.api.forumhub.domain.topico.TopicoStatus;
import com.forumhub.api.forumhub.domain.topico.validacoes.cadastro.ValidarTopicoCadastrado;
import com.forumhub.api.forumhub.domain.usuario.Usuario;
import com.forumhub.api.forumhub.domain.usuario.UsuarioNaoEncontradoException;
import com.forumhub.api.forumhub.domain.usuario.UsuarioRepository;
import com.forumhub.api.forumhub.infra.security.SecurityContexHolderAccess;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class CadastrarTopicoService implements SecurityContexHolderAccess {
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final List<ValidarTopicoCadastrado> validadores;

    @Autowired
    public CadastrarTopicoService(UsuarioRepository usuarioRepository, CursoRepository cursoRepository, List<ValidarTopicoCadastrado> validadores) {
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
        this.validadores = validadores;
    }

    public Topico validar(@Valid TopicoCadastroDTO dados) {
        Optional<Curso> cursoSearched = cursoRepository.findById(dados.idCurso());
        Usuario usuarioSearched = usuarioRepository.findById(authContextUserId).get();

        if (cursoSearched.isEmpty()) {
            throw new CursoNaoEncontradoException();
        }

        // Responsável pela validação. Joga exception apropriada caso alguma falhe.
        validadores.forEach(v -> v.validar(dados));

        return new Topico(null, dados.titulo(), dados.mensagem(), LocalDateTime.now(), TopicoStatus.NAO_RESPONDIDO, usuarioSearched, cursoSearched.get());
    }
}
