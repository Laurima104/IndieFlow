package com.indieflow.dto.tarefa;

import java.time.LocalDate;
import com.indieflow.enums.Prioridade;
import com.indieflow.enums.StatusTarefa;

public record TarefaResponseDTO(Long id, String titulo, String descricao, Prioridade prioridade,
                                StatusTarefa status, LocalDate prazo, String membroNome,
                                String projetoNome) {
    public static TarefaResponseDTO from(com.indieflow.model.Tarefa tarefa) {
        return new TarefaResponseDTO(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(),
                tarefa.getPrioridade(), tarefa.getStatus(), tarefa.getPrazo(),
                tarefa.getMembro() == null ? null : tarefa.getMembro().getNome(),
                tarefa.getProjeto().getNome());
    }
}
