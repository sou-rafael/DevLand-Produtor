package br.com.vemser.devlandapi.teste;

import br.com.vemser.devlandapi.dto.relatorios.DadosNulosDTO;
import br.com.vemser.devlandapi.dto.relatorios.RelatorioPersonalizadoDevDTO;
import br.com.vemser.devlandapi.enums.TipoMensagem;
import br.com.vemser.devlandapi.exceptions.RegraDeNegocioException;
import br.com.vemser.devlandapi.repository.UsuarioRepository;
import br.com.vemser.devlandapi.service.ProdutorService;
import br.com.vemser.devlandapi.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class VerificaPreenchimentoDeEnderecoContatoTodoDia {

    private static final Logger log = LoggerFactory.getLogger(VerificaPreenchimentoDeEnderecoContatoTodoDia.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProdutorService produtorService;


    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws InterruptedException, RegraDeNegocioException {

        List<DadosNulosDTO> usuarioDTO = usuarioRepository.relatorioPersonalizadoDevGeneroDTO2();

        usuarioDTO.forEach(usuario -> {
            try {
                produtorService.enviarMensagemEmail2(usuario, TipoMensagem.CADASTROINCOMPLETO.getTipo());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });


        log.info("deu certo");
        Thread.sleep(1000);
    }
}
