package br.com.vemser.devlandapi.service;

import br.com.vemser.devlandapi.dto.mensagem.EmailDTO;
import br.com.vemser.devlandapi.entity.UsuarioEntity;
import br.com.vemser.devlandapi.enums.TipoMensagem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutorService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Value("${kafka.user}")
    private String usuario;

    @Value("${kafka.topic-email}")
    private String topicoEmail;

    public void enviarMensagemEmail(UsuarioEntity usuarioEntity, String tipo) throws JsonProcessingException {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail(usuarioEntity.getEmail());
        emailDTO.setIdUsuario(usuarioEntity.getIdUsuario());
        emailDTO.setTipoMensagem(TipoMensagem.ofTipo(tipo));
        emailDTO.setFoto(usuarioEntity.getFoto());
        emailDTO.setNome(usuarioEntity.getNome());

        enviarMensagem(emailDTO, topicoEmail);
    }
    private void enviarMensagem(EmailDTO emailDTO, String topico) throws JsonProcessingException {
        String mensagemString = objectMapper.writeValueAsString(emailDTO);

        MessageBuilder<String> stringMessageBuilder = MessageBuilder.withPayload(mensagemString)
                .setHeader(KafkaHeaders.TOPIC, topico)
                .setHeader(KafkaHeaders.MESSAGE_KEY, UUID.randomUUID().toString());
        Message<String> stringMessage = stringMessageBuilder
                .build();

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(stringMessage);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult result) {
                log.info(" Ordem de email enviado para o kafka com o texto: {} ", mensagemString);
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error(" Erro ao publicar ordem de email no kafka com a mensagem: {}", mensagemString, ex);
            }
        });
    }
}
