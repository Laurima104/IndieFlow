package com.indieflow.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.indieflow.enums.Prioridade;
import com.indieflow.enums.StatusTarefa;
import com.indieflow.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    boolean existsByProjeto_Id(Long projetoId);
    List<Tarefa> findByStatus(StatusTarefa status);
    List<Tarefa> findByPrioridade(Prioridade prioridade);
    List<Tarefa> findByProjeto_Id(Long projetoId);
    List<Tarefa> findByMembro_Id(Long membroId);
}
