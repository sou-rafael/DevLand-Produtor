package br.com.vemser.devlandapi.repository;

import br.com.vemser.devlandapi.dto.relatorios.RelatorioPersonalizadoDevDTO;
import br.com.vemser.devlandapi.entity.UsuarioEntity;
import br.com.vemser.devlandapi.enums.Genero;
import br.com.vemser.devlandapi.enums.TipoUsuario;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {



    @Query(value = " select new br.com.vemser.devlandapi.dto.relatorios.RelatorioPersonalizadoDevDTO(" +
            " u.nome," +
            " u.email," +
            " u.areaAtuacao," +
            " u.foto," +
            " u.genero," +
            " u.tipoUsuario," +
            " c.tipo," +
            " c.numero," +
            " c.descricao," +
            " e.cidade," +
            " e.estado," +
            " e.pais," +
            " t.nomeTecnologia" +
            ") " +
            " from usuario u " +
            " full join u.contatos c " +
            " full join u.enderecos e " +
            " full join u.tecnologias t " +
            " where (:areaAtuacao is null OR u.areaAtuacao = :areaAtuacao )")
    Page<RelatorioPersonalizadoDevDTO> relatorioPersonalizadoDevDTO(@Param("areaAtuacao") String areaAtuacao, Pageable pageable);

    @Query(value = " select new br.com.vemser.devlandapi.dto.relatorios.RelatorioPersonalizadoDevDTO(" +
            " u.nome," +
            " u.email," +
            " u.areaAtuacao," +
            " u.foto," +
            " u.genero," +
            " u.tipoUsuario," +
            " c.tipo," +
            " c.numero," +
            " c.descricao," +
            " e.cidade," +
            " e.estado," +
            " e.pais," +
            " t.nomeTecnologia" +
            ") " +
            " from usuario u " +
            " full join u.contatos c " +
            " full join u.enderecos e " +
            " full join u.tecnologias t " +
            " where (:genero is null OR u.genero = :genero )")
    Page<RelatorioPersonalizadoDevDTO> relatorioPersonalizadoDevGeneroDTO(@Param("genero") Genero genero, Pageable pageable);

    @Query(value = " select new br.com.vemser.devlandapi.dto.relatorios.RelatorioPersonalizadoDevDTO(" +
            " u.nome," +
            " u.email," +
            " u.areaAtuacao," +
            " u.foto," +
            " u.genero," +
            " u.tipoUsuario," +
            " c.tipo," +
            " c.numero," +
            " c.descricao," +
            " e.cidade," +
            " e.estado," +
            " e.pais," +
            " t.nomeTecnologia" +
            ") " +
            " from usuario u " +
            " left join u.contatos c " +
            " left join u.enderecos e " +
            " left join u.tecnologias t " +
            " where (c.tipo is null OR c.numero is null OR c.descricao is null OR e.cidade is null OR e.estado is null OR e.pais is null)")
    List<RelatorioPersonalizadoDevDTO> relatorioPersonalizadoDevGeneroDTO2();
}
