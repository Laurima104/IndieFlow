package com.indieflow.dto.bug;

import com.indieflow.enums.Gravidade;
import com.indieflow.enums.StatusTarefa;

public record BugResponseDTO(Long id, String titulo, String descricao, Gravidade gravidade,
                             StatusTarefa status, Long projetoId, String projetoNome) {
    public static BugResponseDTO from(com.indieflow.model.Bug bug) {
        return new BugResponseDTO(bug.getId(), bug.getTitulo(), bug.getDescricao(), bug.getGravidade(),
                bug.getStatus(), bug.getProjeto().getId(), bug.getProjeto().getNome());
    }
}
