package com.indieflow.dto.membro;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MembroRequestDTO(
        @NotBlank @Size(max = 50) String nome,
        @NotBlank @Email @Size(max = 50) String email,
        @NotBlank @Size(max = 50) String especialidade) {
}
