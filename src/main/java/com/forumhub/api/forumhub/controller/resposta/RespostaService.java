package com.forumhub.api.forumhub.controller.resposta;

import com.forumhub.api.forumhub.domain.resposta.*;
import com.forumhub.api.forumhub.domain.topico.Topico;
import com.forumhub.api.forumhub.domain.topico.TopicoNaoEncontradoException;
import com.forumhub.api.forumhub.domain.topico.TopicoRepository;
import com.forumhub.api.forumhub.domain.usuario.Usuario;
import com.forumhub.api.forumhub.domain.usuario.UsuarioRepository;
import com.forumhub.api.forumhub.infra.security.OwnerValidators.RespostaOwnerValidator;
import com.forumhub.api.forumhub.infra.security.SecurityContexHolderAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RespostaService implements SecurityContexHolderAccess {
    private final TopicoRepository topicoRepository;
    private final RespostaRepository respostaRepository;
    private final UsuarioRepository usuarioRepository;
    private final RespostaOwnerValidator validator;

    @Autowired
    public RespostaService(TopicoRepository topicoRepository, RespostaRepository respostaRepository, UsuarioRepository usuarioRepository, RespostaOwnerValidator validator) {
        this.topicoRepository = topicoRepository;
        this.respostaRepository = respostaRepository;
        this.usuarioRepository = usuarioRepository;
        this.validator = validator;
    }

    public List<RespostaDetalhamentoDTO> buscarRespostas(Long topicoId) {
        //Checks if the original Topic exists
        Optional<Topico> topicoOrigem = topicoRepository.findById(topicoId);
        if (topicoOrigem.isEmpty()) {
            throw new TopicoNaoEncontradoException();
        }
        return respostaRepository.findAllByTopicoId(topicoId);
    }

    public RespostaDetalhamentoDTO cadastrarResposta(RespostaDTO respostaDTO, long topicoId) {
        //Checks if the original Topic exists
        Optional<Topico> topicoOrigem = topicoRepository.findById(topicoId);
        Usuario respostaAutor = usuarioRepository.findById(authContextUserId).get();
        if (topicoOrigem.isEmpty()) {
            throw new TopicoNaoEncontradoException();
        }
        //Saves new Resposta
        Resposta resposta = new Resposta(null, respostaDTO.mensagem(), topicoOrigem.get(), LocalDateTime.now(), respostaAutor, false);
        respostaRepository.save(resposta);
        //Returns DTO
        return new RespostaDetalhamentoDTO(
                respostaDTO.mensagem(),
                resposta.getDataCriacao(),
                resposta.getAutor().getNome(),
                resposta.getAutor().getId(),
                false);
    }

    public void deletarResposta(Long idResposta) {
        Optional<Resposta> resposta = respostaRepository.findById(idResposta);
        if (resposta.isEmpty()) {
            throw new RespostaNaoEncontradaException();
        }
        //Checking if logged user is owner and proper author of the Resposta
        validator.validate(resposta.get());
        respostaRepository.deleteById(idResposta);
    }
}
