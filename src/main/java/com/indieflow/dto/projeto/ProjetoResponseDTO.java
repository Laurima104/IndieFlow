package com.indieflow.dto.projeto;

import java.time.LocalDate;
import com.indieflow.enums.StatusProjeto;
import com.indieflow.model.Projeto;

public record ProjetoResponseDTO(Long id, String nome, String descricao, String genero,
        StatusProjeto status, LocalDate dataInicio) {
    public static ProjetoResponseDTO from(Projeto projeto) {
        return new ProjetoResponseDTO(
            projeto.getId(), 
            projeto.getNome(), 
            projeto.getDescricao(),
            projeto.getGenero(), 
            projeto.getStatus(), 
            projeto.getDataInicio()
        );
    }
}
