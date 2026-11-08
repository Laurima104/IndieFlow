package com.indieflow.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.indieflow.dto.projeto.ProjetoRequestDTO;
import com.indieflow.model.Projeto;
import com.indieflow.repository.BugRepository;
import com.indieflow.repository.ProjetoRepository;
import com.indieflow.repository.TarefaRepository;

@Service
@Transactional
public class ProjetoService {
    private final ProjetoRepository projetos;
    private final TarefaRepository tarefas;
    private final BugRepository bugs;

    public ProjetoService(ProjetoRepository projetos, TarefaRepository tarefas, BugRepository bugs) {
        this.projetos = projetos;
        this.tarefas = tarefas;
        this.bugs = bugs;
    }

    @Transactional(readOnly = true)
    public List<Projeto> listarTodos() { return projetos.findAll(); }

    @Transactional(readOnly = true)
    public Projeto buscarPorId(Long id) {
        return projetos.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto " + id + " não encontrado"));
    }

    public Projeto criar(ProjetoRequestDTO dto) {
        Projeto projeto = new Projeto();
        aplicar(projeto, dto);
        projeto.setDataInicio(LocalDate.now());
        return projetos.save(projeto);
    }

    public Projeto atualizar(Long id, ProjetoRequestDTO dto) {
        Projeto projeto = buscarPorId(id);
        aplicar(projeto, dto);
        return projetos.save(projeto);
    }

    public void excluir(Long id) {
        Projeto projeto = buscarPorId(id);
        if (tarefas.existsByProjeto_Id(id) || bugs.existsByProjeto_Id(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Não é possível excluir projeto com tarefas ou bugs vinculados");
        }
        projetos.delete(projeto);
    }

    private void aplicar(Projeto projeto, ProjetoRequestDTO dto) {
        projeto.setNome(dto.nome().trim());
        projeto.setDescricao(dto.descricao());
        projeto.setGenero(dto.genero().trim());
        projeto.setStatus(dto.status());
    }
}
