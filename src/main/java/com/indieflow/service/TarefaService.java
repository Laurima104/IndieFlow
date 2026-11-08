package com.indieflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.indieflow.enums.Prioridade;
import com.indieflow.enums.StatusTarefa;
import com.indieflow.model.Tarefa;
import com.indieflow.repository.TarefaRepository;

@Service 
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    public TarefaService(TarefaRepository tarefaRepository){
        this.tarefaRepository = tarefaRepository;
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
        return tarefaRepository.findByProjeto(projeto_id);
    }

    public List<Tarefa> listarPorMembro(Long membro_id){
        return tarefaRepository.findByMembro(membro_id);
    }
}
