package com.forumhub.api.forumhub.controller.topico.services;

import com.forumhub.api.forumhub.domain.curso.Curso;
import com.forumhub.api.forumhub.domain.curso.CursoNaoEncontradoException;
import com.forumhub.api.forumhub.domain.curso.CursoRepository;
import com.forumhub.api.forumhub.domain.topico.TopicoCadastroDTO;
import com.forumhub.api.forumhub.domain.topico.Topico;
import com.forumhub.api.forumhub.domain.topico.TopicoStatus;
import com.forumhub.api.forumhub.domain.topico.validacoes.cadastro.ValidarTopicoCadastrado;
import com.forumhub.api.forumhub.domain.usuario.Usuario;
import com.forumhub.api.forumhub.domain.usuario.UsuarioNaoEncontradoException;
import com.forumhub.api.forumhub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class CadastrarTopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private List<ValidarTopicoCadastrado> validadores;

    public Topico validar(@Valid TopicoCadastroDTO dados) {
        Optional<Usuario> userSearched = usuarioRepository.findById(dados.idAutor());
        Optional<Curso> cursoSearched = cursoRepository.findById(dados.idCurso());

        if (userSearched.isEmpty()) {
            throw new UsuarioNaoEncontradoException();
        }
        if (cursoSearched.isEmpty()) {
            throw new CursoNaoEncontradoException();
        }

        Topico topicoCriado = new Topico(null, dados.titulo(), dados.mensagem(), LocalDateTime.now(), TopicoStatus.NAO_RESPONDIDO, userSearched.get(), cursoSearched.get());

        validadores.forEach(v -> v.validar(topicoCriado));

        return topicoCriado;
    }
}
