package com.indieflow.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.indieflow.dto.tarefa.TarefaResponseDTO;
import com.indieflow.enums.Prioridade;
import com.indieflow.enums.StatusTarefa;
import com.indieflow.model.Tarefa;
import com.indieflow.service.TarefaService;

@RestController 
@RequestMapping ("/tarefas")
public class TarefaController {
    private final TarefaService tarefaService;
    public TarefaController(TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }
    
    @GetMapping 
    public List<TarefaResponseDTO> listarTarefas(@RequestParam (name ="status", required = false) StatusTarefa status, @RequestParam (name ="prioridade", required = false) Prioridade prioridade, @RequestParam (name ="projeto_id", required = false) Long projeto_id, @RequestParam (name ="membro_id", required = false) Long membro_id){
        if(status!=null){
            return tarefaService.listarPorStatus(status)
                .stream()
                .map(TarefaResponseDTO::from)
                .toList();
        }
        if(prioridade!=null){
            return tarefaService.listarPorPrioridade(prioridade)
                .stream()
                .map(TarefaResponseDTO::from)
                .toList();
        }
        if(projeto_id!=null){
            return tarefaService.listarPorProjeto(projeto_id)
                .stream()
                .map(TarefaResponseDTO::from)
                .toList();
        }
        if(membro_id!=null){
            return tarefaService.listarPorMembro(membro_id)
                .stream()
                .map(TarefaResponseDTO::from)
                .toList();
        }
        return tarefaService.listarTodos()
            .stream()
            .map(TarefaResponseDTO::from)
            .toList();
    }

    @GetMapping ("/{id}")
    public TarefaResponseDTO buscarPorId(@PathVariable Long id){
        return TarefaResponseDTO.from(tarefaService.listarPorId(id));
    }

    @PostMapping 
    @ResponseStatus (HttpStatus.CREATED)
    public TarefaResponseDTO abrirTarefa(@RequestBody Tarefa tarefa){
        return TarefaResponseDTO.from(tarefaService.abrirTarefa(tarefa));
    }

    @PutMapping ("/{id}/membro/{membro_id}")
    public TarefaResponseDTO atribuirMembro(@PathVariable long id, @PathVariable Long membro_id){
        return TarefaResponseDTO.from(tarefaService.atribuirMembro(id, membro_id));
    }

    @PutMapping ("/{id}/cancelar")
    public TarefaResponseDTO cancelar(@PathVariable Long id){
        return TarefaResponseDTO.from(tarefaService.cancelarTarefa(id));
    }

    @PutMapping ("/{id}/concluir")
    public TarefaResponseDTO concluir(@PathVariable long id){
        return TarefaResponseDTO.from(tarefaService.finalizarTarefa(id));
    }
}
