package com.indieflow.dto.membro;

public record MembroResponseDTO(Long id, String nome, String email, String especialidade) {
    public static MembroResponseDTO from(com.indieflow.model.Membro membro) {
        return new MembroResponseDTO(membro.getId(), membro.getNome(), membro.getEmail(), membro.getEspecialidade());
    }
}
