package br.com.alura.forum.repository;


import br.com.alura.forum.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

//    Nas classes Repository, os métodos que recebem um pageable como parâmetro retornam objetos do tipo Page<>, ao invés de List<>;

    Page<Topico> findByCurso_Nome(String nomeCurso, Pageable paginacao);

//    Para criar manualmente a consulta com JPQL, devemos utilizar a anotação @Query;
//    se houver necessidade de alterar nomenclatura padrão spring do método necessário escrever query em jpql
//    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
//    List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso")String nomeCurso);
}
