package br.com.vemser.devlandapi.dto.acesso;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
@Document(collection = "log_acesso")
@Getter
@Setter
public class LogAcessoDTO {
    private String nome;

    @Field(value = "_id")
    private String email;

    private LocalDateTime data;
}
