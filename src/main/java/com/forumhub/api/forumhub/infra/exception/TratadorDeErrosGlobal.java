package com.forumhub.api.forumhub.infra.exception;

import com.forumhub.api.forumhub.domain.resposta.RespostaNaoEncontradaException;
import com.forumhub.api.forumhub.domain.registro.EmailJaExistenteException;
import com.forumhub.api.forumhub.domain.curso.CursoNaoEncontradoException;
import com.forumhub.api.forumhub.domain.topico.TopicoNaoEncontradoException;
import com.forumhub.api.forumhub.domain.topico.validacoes.cadastro.TopicoDuplicadoException;
import com.forumhub.api.forumhub.domain.usuario.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ExceptionHandler(RespostaNaoEncontradaException.class)
    public ResponseEntity<RespostaNaoEncontradaException> tratarRespostaNaoEncontradaException(RespostaNaoEncontradaException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<String> mensagensDeErro = e.getBindingResult().getFieldErrors().stream()
                .map(erro -> String.format("Campo '%s': %s", erro.getField(), erro.getDefaultMessage()))
                .toList();

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Erro de validação");
        resposta.put("erros", mensagensDeErro);

        return ResponseEntity.badRequest().body(resposta);
    }
//
//    @ExceptionHandler(JWTVerificationException.class)
//    public ResponseEntity<JWTVerificationException> tratarErroJWTVerificationException(JWTVerificationException e) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e);
//    }
//
//    @ExceptionHandler(TokenExpiredException.class)
//    public ResponseEntity<TokenExpiredException> tratarErroTokenExpiredException(TokenExpiredException e) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e);
//    }
}
