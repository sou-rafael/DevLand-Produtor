package br.com.vemser.devlandapi.repository;

import br.com.vemser.devlandapi.dto.acesso.LogAcessoDTO;
import lombok.extern.java.Log;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import javax.swing.text.Document;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public interface LogAcessoRepository extends MongoRepository<LogAcessoDTO,Integer> {

    @Aggregation(pipeline = "{\n" +
            "    $group: {\n" +
            "        _id: '$email',\n" +
            "        nome: {\n" +
            "            $first: '$nome'\n" +
            "        },\n" +
            "        data: {\n" +
            "            $max: '$data'\n" +
            "        }\n" +
            "    }\n" +
            "}")
    List<LogAcessoDTO> retornarAggregation();
}
