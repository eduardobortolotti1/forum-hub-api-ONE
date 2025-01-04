package com.forumhub.api.forumhub.domain.topico.validacoes.cadastro;

import com.forumhub.api.forumhub.domain.topico.Topico;
import com.forumhub.api.forumhub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidarTopicoUnico implements ValidarTopicoCadastrado {
    private final TopicoRepository topicRepository;

    @Autowired
    public ValidarTopicoUnico(TopicoRepository topicRepository) {
        this.topicRepository = topicRepository;
    }
    
    public void validar(Topico topico) {
        var duplicadoSearch = topicRepository.findByTituloAndMensagem(topico.getTitulo(), topico.getMensagem());
        if (duplicadoSearch.isPresent() && !duplicadoSearch.get().isEmpty()) {
            throw new TopicoDuplicadoException("Já existe um tópico com o mesmo título e mensagem. e utilizar um título ou mensagem diferentes.");
        }
    }
}
