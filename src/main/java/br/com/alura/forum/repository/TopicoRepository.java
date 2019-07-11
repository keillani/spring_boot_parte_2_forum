package br.com.alura.forum.repository;


import br.com.alura.forum.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByCurso_Nome(String nomeCurso);

//    Para criar manualmente a consulta com JPQL, devemos utilizar a anotação @Query;
//    se houver necessidade de alterar nomenclatura padrão spring do método necessário escrever query em jpql
//    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
//    List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso")String nomeCurso);
}
