package com.indieflow.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.indieflow.dto.bug.BugRequestDTO;
import com.indieflow.model.Bug;
import com.indieflow.model.Projeto;
import com.indieflow.repository.BugRepository;
import com.indieflow.repository.ProjetoRepository;

@Service
@Transactional
public class BugService {
    private final BugRepository bugs;
    private final ProjetoRepository projetos;

    public BugService(BugRepository bugs, ProjetoRepository projetos) {
        this.bugs = bugs;
        this.projetos = projetos;
    }

    @Transactional(readOnly = true)
    public List<Bug> listarTodos() { return bugs.findAll(); }

    @Transactional(readOnly = true)
    public Bug buscarPorId(Long id) {
        return bugs.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bug " + id + " não encontrado"));
    }

    public Bug criar(BugRequestDTO dto) {
        Bug bug = new Bug();
        aplicar(bug, dto);
        return bugs.save(bug);
    }

    public Bug atualizar(Long id, BugRequestDTO dto) {
        Bug bug = buscarPorId(id);
        aplicar(bug, dto);
        return bugs.save(bug);
    }

    public void excluir(Long id) { bugs.delete(buscarPorId(id)); }

    private void aplicar(Bug bug, BugRequestDTO dto) {
        Projeto projeto = projetos.findById(dto.projetoId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto " + dto.projetoId() + " não encontrado"));
        bug.setTitulo(dto.titulo().trim());
        bug.setDescricao(dto.descricao());
        bug.setGravidade(dto.gravidade());
        bug.setStatus(dto.status());
        bug.setProjeto(projeto);
    }
}
