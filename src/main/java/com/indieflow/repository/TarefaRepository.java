package com.indieflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indieflow.enums.Prioridade;
import com.indieflow.enums.StatusTarefa;
import com.indieflow.model.Tarefa;

@Repository 
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
    List<Tarefa> findByStatus(StatusTarefa status);

    List<Tarefa> findByPrioridade(Prioridade prioridade);
    
    List<Tarefa> findByProjeto(Long projeto_id);

    List<Tarefa> findByMembro(Long membro_id);

} 