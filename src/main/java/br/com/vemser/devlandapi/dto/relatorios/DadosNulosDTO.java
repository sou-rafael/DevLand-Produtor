package br.com.vemser.devlandapi.dto.relatorios;

import br.com.vemser.devlandapi.enums.Genero;
import br.com.vemser.devlandapi.enums.TipoClassificacao;
import br.com.vemser.devlandapi.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosNulosDTO {

    private String nome;

    private String email;

    private TipoClassificacao tipo;

    private String numero;

    private String descricao;

    private String cidade;

    private String estado;

    private String pais;
}
