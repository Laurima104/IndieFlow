package com.indieflow.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.indieflow.dto.tarefa.TarefaRequestDTO;
import com.indieflow.enums.Prioridade;
import com.indieflow.enums.StatusTarefa;
import com.indieflow.model.Membro;
import com.indieflow.model.Projeto;
import com.indieflow.model.Tarefa;
import com.indieflow.repository.MembroRepository;
import com.indieflow.repository.ProjetoRepository;
import com.indieflow.repository.TarefaRepository;

@Service
@Transactional
public class TarefaService {
    private final TarefaRepository tarefas;
    private final ProjetoRepository projetos;
    private final MembroRepository membros;

    public TarefaService(TarefaRepository tarefas, ProjetoRepository projetos, MembroRepository membros) {
        this.tarefas = tarefas;
        this.projetos = projetos;
        this.membros = membros;
    }

    @Transactional(readOnly = true)
    public List<Tarefa> listarTodos() { return tarefas.findAll(); }

    @Transactional(readOnly = true)
    public List<Tarefa> listarPorStatus(StatusTarefa status) { return tarefas.findByStatus(status); }

    @Transactional(readOnly = true)
    public List<Tarefa> listarPorPrioridade(Prioridade prioridade) { return tarefas.findByPrioridade(prioridade); }

    @Transactional(readOnly = true)
    public List<Tarefa> listarPorProjeto(Long projetoId) { return tarefas.findByProjeto_Id(projetoId); }

    @Transactional(readOnly = true)
    public List<Tarefa> listarPorMembro(Long membroId) { return tarefas.findByMembro_Id(membroId); }

    @Transactional(readOnly = true)
    public Tarefa buscarPorId(Long id) {
        return tarefas.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa " + id + " não encontrada"));
    }

    public Tarefa criar(TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        aplicar(tarefa, dto);
        return tarefas.save(tarefa);
    }

    public Tarefa atualizar(Long id, TarefaRequestDTO dto) {
        Tarefa tarefa = buscarPorId(id);
        aplicar(tarefa, dto);
        return tarefas.save(tarefa);
    }

    public void excluir(Long id) { tarefas.delete(buscarPorId(id)); }

    private void aplicar(Tarefa tarefa, TarefaRequestDTO dto) {
        Projeto projeto = projetos.findById(dto.projetoId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto " + dto.projetoId() + " não encontrado"));
        Membro membro = dto.membroId() == null ? null : membros.findById(dto.membroId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Membro " + dto.membroId() + " não encontrado"));
        tarefa.setTitulo(dto.titulo().trim());
        tarefa.setDescricao(dto.descricao());
        tarefa.setPrioridade(dto.prioridade());
        tarefa.setStatus(dto.status());
        tarefa.setPrazo(dto.prazo());
        tarefa.setProjeto(projeto);
        tarefa.setMembro(membro);
    }
}
