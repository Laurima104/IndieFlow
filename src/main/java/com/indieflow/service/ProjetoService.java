package com.indieflow.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.indieflow.enums.StatusProjeto;
import com.indieflow.model.Projeto;
import com.indieflow.repository.ProjetoRepository;

@Service 
public class ProjetoService {
    private final ProjetoRepository projetoRepository;
    public ProjetoService(ProjetoRepository projetoRepository){
        this.projetoRepository = projetoRepository;
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

    public Projeto iniciarProjeto(Projeto projeto){
        projeto.setData_inicio(LocalDate.now());
        projeto.setStatus(StatusProjeto.PLANEJAMENTO);
        return projetoRepository.save(projeto);
    }

    
}
