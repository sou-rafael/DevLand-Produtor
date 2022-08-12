package br.com.vemser.devlandapi.dto.acesso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class LogAcessoRetornoDTO {

    private String nome;

    @Field(value = "_id")
    private String email;

    private LocalDateTime data;
}
