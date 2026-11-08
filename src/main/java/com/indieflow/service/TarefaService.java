package com.indieflow.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.indieflow.enums.Prioridade;
import com.indieflow.enums.StatusTarefa;
import com.indieflow.model.Membro;
import com.indieflow.model.Tarefa;
import com.indieflow.repository.TarefaRepository;

@Service 
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final MembroService membroService;
    public TarefaService(TarefaRepository tarefaRepository, MembroService membroService){
        this.tarefaRepository = tarefaRepository;
        this.membroService = membroService;
    }

    public List<Tarefa> listarTodos(){
        return tarefaRepository.findAll();
    }

    public List<Tarefa> listarPorStatus(StatusTarefa status){
        return tarefaRepository.findByStatus(status);
    }

    public List<Tarefa> listarPorPrioridade(Prioridade prioridade){
        return tarefaRepository.findByPrioridade(prioridade);
    }

    public List<Tarefa> listarPorProjeto(Long projeto_id){
        return tarefaRepository.findByProjeto_Id(projeto_id);
    }

    public List<Tarefa> listarPorMembro(Long membro_id){
        return tarefaRepository.findByMembro_Id(membro_id);
    }

    public Tarefa listarPorId(Long id){
        return tarefaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Tarefa não localizada " + id
            ));
    }

    public Tarefa abrirTarefa(Tarefa tarefa){
        tarefa.setStatus(StatusTarefa.PENDENTE);

        return tarefaRepository.save(tarefa);
    }

    public Tarefa atribuirMembro(Long tarefa_id, Long membro_id){
        Tarefa tarefa = listarPorId(tarefa_id);
        Membro membro = membroService.buscarPorId(membro_id);
        
        tarefa.setMembro(membro);
        tarefa.setStatus(StatusTarefa.EM_ANDAMENTO);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa finalizarTarefa(Long id){
        Tarefa tarefa = listarPorId(id);

        tarefa.setStatus(StatusTarefa.CONCLUIDA);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa cancelarTarefa(Long id){
        Tarefa tarefa = listarPorId(id);

        tarefa.setStatus(StatusTarefa.CANCELADA);
        return tarefaRepository.save(tarefa);
    }
}
