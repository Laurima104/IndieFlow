package com.indieflow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Projeto> listar(@RequestParam(required = false) StatusProjeto status){
        if(status != null){
            return projetoService.buscarPorStatus(status);
        }
        return projetoService.listarTodos();
    }

    @GetMapping ("/{id}")
    public Projeto buscar(@PathVariable Long id){
        return projetoService.buscarPorId(id);
    }

    
}
