package br.com.vemser.devlandapi.dto.usuario;

import br.com.vemser.devlandapi.enums.Genero;
import br.com.vemser.devlandapi.enums.TipoUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDTO {

    @Schema(description = "nome do desenvolvedor ou empresa", example = "João")
    @NotEmpty
    @Size(min = 3, max = 100)
    private String nome;

    @Schema(description = "email do desenvolvedor ou empresa", example = "joão@gmail.com")
    @NotEmpty
    @Size(min = 10, max = 100)
    private String email;

    @Schema(description = "area de atuação do desenvolvedor ou empresa", example = "Java")
    @NotEmpty
    @Size(min = 4, max = 100)
    private String areaAtuacao;

    @Schema(description = "numero de registro (CPF ou CNPJ) sem pontos ou traços", example = "xxxxxxxxxxx")
    @NotEmpty
    @Size(min = 11, max = 14)
    private String cpfCnpj;

    @Schema(description = "foto do desenvolvedor ou empresa", example = "https://i.imgur.com/3zry15Q.jpg")
    @NotEmpty
    @Size(min = 1, max = 1000)
    private String foto;

    @Schema(description = "Genero do usuário", example = "MASCULINO ou FEMININO")
    @NotNull
    private Genero genero;

    @Schema(description = "perfil de usuário", example = "DEV ou EMPRESA")
    @NotNull
    private TipoUsuario tipoUsuario;
}
