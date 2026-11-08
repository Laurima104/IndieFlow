package com.indieflow.dto.projeto;

import com.indieflow.enums.StatusProjeto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProjetoRequestDTO(
        @NotBlank @Size(max = 25) String nome,
        @Size(max = 200) String descricao,
        @NotBlank @Size(max = 20) String genero) {
}
