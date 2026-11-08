package com.indieflow.dto.tarefa;

import java.time.LocalDate;
import com.indieflow.enums.Prioridade;
import com.indieflow.enums.StatusTarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TarefaRequestDTO(
        @NotBlank @Size(max = 100) String titulo,
        @Size(max = 500) String descricao,
        @NotNull Prioridade prioridade,
        @NotNull StatusTarefa status,
        LocalDate prazo,
        @NotNull @Positive Long projetoId) {
}
