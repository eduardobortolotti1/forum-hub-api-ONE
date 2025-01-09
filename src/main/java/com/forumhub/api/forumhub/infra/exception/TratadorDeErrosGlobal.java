package com.forumhub.api.forumhub.infra.exception;

import com.forumhub.api.forumhub.controller.topico.EmailJaExistenteException;
import com.forumhub.api.forumhub.domain.curso.CursoNaoEncontradoException;
import com.forumhub.api.forumhub.domain.topico.TopicoNaoEncontradoException;
import com.forumhub.api.forumhub.domain.topico.validacoes.cadastro.TopicoDuplicadoException;
import com.forumhub.api.forumhub.domain.usuario.UsuarioNaoEncontradoException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrosGlobal {
    @ExceptionHandler(TopicoDuplicadoException.class)
    public ResponseEntity<TopicoDuplicadoException> tratarErroTopicoDuplicado(TopicoDuplicadoException e) {
        return ResponseEntity.badRequest().body(e);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<UsuarioNaoEncontradoException> tratarErroUsuarioNaoEncontrado(UsuarioNaoEncontradoException e) {
        return ResponseEntity.badRequest().body(e);
    }

    @ExceptionHandler(CursoNaoEncontradoException.class)
    public ResponseEntity<CursoNaoEncontradoException> tratarErroCursoNaoEncontrado(CursoNaoEncontradoException e) {
        return ResponseEntity.badRequest().body(e);
    }

    @ExceptionHandler(TopicoNaoEncontradoException.class)
    public ResponseEntity<TopicoNaoEncontradoException> tratarErroTopicoNaoEncontrado(TopicoNaoEncontradoException e) {
        return ResponseEntity.badRequest().body(e);
    }

    @ExceptionHandler(EmailJaExistenteException.class)
    public ResponseEntity<EmailJaExistenteException> tratarErroEmailJaExistente(EmailJaExistenteException e) {
        return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(e);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<AccessDeniedException> tratarErroAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(e);
    }
}
