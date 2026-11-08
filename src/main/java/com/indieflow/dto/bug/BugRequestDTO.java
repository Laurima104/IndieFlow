package com.indieflow.dto.bug;

import com.indieflow.enums.Gravidade;
import com.indieflow.enums.StatusTarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BugRequestDTO(
        @NotBlank @Size(max = 25) String titulo,
        @Size(max = 150) String descricao,
        @NotNull Gravidade gravidade,
        @NotNull StatusTarefa status,
        @NotNull @Positive Long projetoId) {
}
