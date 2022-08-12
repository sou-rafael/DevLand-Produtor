package br.com.vemser.devlandapi.schedule;

import br.com.vemser.devlandapi.dto.relatorios.DadosNulosDTO;
import br.com.vemser.devlandapi.enums.TipoMensagem;
import br.com.vemser.devlandapi.exceptions.RegraDeNegocioException;
import br.com.vemser.devlandapi.repository.UsuarioRepository;
import br.com.vemser.devlandapi.service.ProdutorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class VerificaPreenchimentoDeEnderecoContatoTodaSegunda {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutorService produtorService;

    @Scheduled(cron = "0 0 8 * * MON" )
    public void reportCurrentTime() {
        List<DadosNulosDTO> usuarioDTO = usuarioRepository.verificadorUsuariosComDadosNulos();
        usuarioDTO.forEach(usuario -> {
            try {
                produtorService.enviarMensagemEmail2(usuario, TipoMensagem.CADASTROINCOMPLETO.getTipo());
                Thread.sleep(5000);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
