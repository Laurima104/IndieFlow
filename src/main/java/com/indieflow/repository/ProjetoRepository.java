package com.indieflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.indieflow.enums.StatusProjeto;
import com.indieflow.model.Projeto;
import com.indieflow.model.Tarefa;

@Repository 
public interface ProjetoRepository extends JpaRepository<Projeto, Long>{
    
    List<Projeto> findByStatus(StatusProjeto status);

    @Query("select t from Tarefa t where t.projeto.id = :projetoId")
    List<Tarefa> findTarefasByProjeto(@Param("projetoId") Long projetoId);

}
