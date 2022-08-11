package br.com.vemser.devlandapi.repository;

import br.com.vemser.devlandapi.dto.acesso.LogAcessoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogAcessoRepository extends MongoRepository<LogAcessoDTO,Integer> {
}
