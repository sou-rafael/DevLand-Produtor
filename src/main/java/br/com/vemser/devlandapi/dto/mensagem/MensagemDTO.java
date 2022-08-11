package br.com.vemser.devlandapi.dto.mensagem;

import br.com.vemser.devlandapi.entity.UsuarioEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensagemDTO {

    private UsuarioEntity usuarioEntity;

    private String tipo;

    @Schema(hidden = true)
    private LocalDateTime dataCriacao;
}
