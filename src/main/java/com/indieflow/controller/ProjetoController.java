package com.indieflow.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.indieflow.dto.projeto.ProjetoRequestDTO;
import com.indieflow.dto.projeto.ProjetoResponseDTO;
import com.indieflow.enums.StatusProjeto;
import com.indieflow.model.Projeto;
import com.indieflow.service.ProjetoService;

@RestController 
@RequestMapping ("/projetos")
public class ProjetoController {
    private final ProjetoService projetoService;
    public ProjetoController(ProjetoService projetoService){
        this.projetoService=projetoService;
    }

    @GetMapping 
    public List<ProjetoResponseDTO> listar(@RequestParam(name ="status", required = false) StatusProjeto status){
        if(status != null){
            return projetoService.buscarPorStatus(status)
                .stream()
                .map(ProjetoResponseDTO::from)
                .toList();
        }
        return projetoService.listarTodos()
                .stream()
                .map(ProjetoResponseDTO::from)
                .toList();
    }

    @GetMapping ("/{id}")
    public ProjetoResponseDTO buscar(@PathVariable(name="id") Long id){
        return ProjetoResponseDTO.from(projetoService.buscarPorId(id));
    }

    @PostMapping 
    @ResponseStatus (HttpStatus.CREATED)
    public ResponseEntity<ProjetoResponseDTO> iniciarProjeto(@RequestBody ProjetoRequestDTO dto){
        Projeto projeto = projetoService.iniciarProjeto(dto);
        return ResponseEntity.status(201).body(ProjetoResponseDTO.from(projeto));
    }

    @PutMapping ("/{id}/concluir")
    public ProjetoResponseDTO concluirProjeto(@PathVariable(name="id") Long id){
        return ProjetoResponseDTO.from(projetoService.finalizarProjeto(id));
    }

    @PutMapping ("/{id}/cancelar")
    public ProjetoResponseDTO cancelarProjeto(@PathVariable(name="id") long id){
        return ProjetoResponseDTO.from(projetoService.cancelarProjeto(id));
    }
}
