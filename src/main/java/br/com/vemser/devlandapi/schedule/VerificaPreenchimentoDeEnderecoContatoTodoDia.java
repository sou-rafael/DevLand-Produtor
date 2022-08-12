package br.com.vemser.devlandapi.schedule;

import br.com.vemser.devlandapi.dto.relatorios.DadosNulosDTO;
import br.com.vemser.devlandapi.enums.TipoMensagem;
import br.com.vemser.devlandapi.exceptions.RegraDeNegocioException;
import br.com.vemser.devlandapi.repository.UsuarioRepository;
import br.com.vemser.devlandapi.service.ProdutorService;
import br.com.vemser.devlandapi.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
@Slf4j
public class VerificaPreenchimentoDeEnderecoContatoTodoDia {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProdutorService produtorService;


    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() throws InterruptedException, RegraDeNegocioException {
        List<DadosNulosDTO> usuarioDTO = usuarioRepository.relatorioPersonalizadoDevGeneroDTO2();
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
        log.info("deu certo");
    }
}
