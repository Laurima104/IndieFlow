package com.indieflow.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.indieflow.enums.StatusProjeto;
import com.indieflow.enums.StatusTarefa;
import com.indieflow.model.Projeto;
import com.indieflow.model.Tarefa;
import com.indieflow.repository.ProjetoRepository;

@Service 
public class ProjetoService {
    private final ProjetoRepository projetoRepository;
    private final TarefaService tarefaService;
    public ProjetoService(ProjetoRepository projetoRepository, TarefaService tarefaService){
        this.projetoRepository = projetoRepository;
        this.tarefaService=tarefaService;
    }

    public List<Projeto> listarTodos(){
        return projetoRepository.findAll();
    }

    public Projeto buscarPorId(Long id){
        return projetoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Projeto não localizado:" + id
            ));
    }

    public List<Projeto> buscarPorStatus(StatusProjeto status){
        return projetoRepository.findByStatus(status);
    }

    public Projeto iniciarProjeto(Projeto projeto){
        projeto.setDataInicio(LocalDate.now());
        projeto.setStatus(StatusProjeto.PLANEJAMENTO);
        return projetoRepository.save(projeto);
    }

    public Projeto desenvolverProjeto(Long projeto_id){
        Projeto projeto = buscarPorId(projeto_id);

        projeto.setStatus(StatusProjeto.EM_DESENVOLVIMENTO);
        return projetoRepository.save(projeto);
    }

    public Projeto pausarProjeto(Long projeto_id){
        Projeto projeto = buscarPorId(projeto_id);

        projeto.setStatus(StatusProjeto.PAUSADO);
        return projetoRepository.save(projeto);
    }

    private void validarProjetoFinalizadoOuCancelado(Projeto projeto){
        if(projeto.getStatus()==StatusProjeto.CONCLUIDO || projeto.getStatus()==StatusProjeto.CANCELADO){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Projeto ja " + projeto.getStatus()
            );
        }
    }

    public Projeto cancelarProjeto(Long projeto_id){
        Projeto projeto = buscarPorId(projeto_id);
        validarProjetoFinalizadoOuCancelado(projeto);

        projeto.setStatus(StatusProjeto.CANCELADO);
        return projetoRepository.save(projeto);
    }

    private void validarTarefasConcluidas(List<Tarefa> tarefas){
        for (Tarefa tarefa : tarefas){
            if (tarefa.getStatus()==StatusTarefa.PENDENTE || tarefa.getStatus()==StatusTarefa.EM_ANDAMENTO){
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Tarefa não Finalizada no Projeto."
                );
            }
        }
        
    }

    public Projeto finalizarProjeto(Long projeto_id){
        Projeto projeto = buscarPorId(projeto_id);
        validarProjetoFinalizadoOuCancelado(projeto);
        validarTarefasConcluidas(tarefaService.listarPorProjeto(projeto_id));

        projeto.setStatus(StatusProjeto.CONCLUIDO);
        return projetoRepository.save(projeto);
    }
}
