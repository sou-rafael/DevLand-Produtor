package br.com.vemser.devlandapi.dto.mensagem;

import br.com.vemser.devlandapi.enums.TipoMensagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {

    private Integer idUsuario;

    private String nome;

    private String email;

    private String foto;

    TipoMensagem tipoMensagem;
}
