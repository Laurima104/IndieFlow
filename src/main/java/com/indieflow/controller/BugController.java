package com.indieflow.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.indieflow.dto.bug.BugRequestDTO;
import com.indieflow.dto.bug.BugResponseDTO;
import com.indieflow.service.BugService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/bugs")
public class BugController {
    private final BugService service;

    public BugController(BugService service) {
        this.service = service;
    }

    @GetMapping
    public List<BugResponseDTO> listar() {
        return service.listarTodos().stream().map(BugResponseDTO::from).toList();
    }

    @GetMapping("/{id}")
    public BugResponseDTO buscar(@PathVariable(name="id") Long id) {
        return BugResponseDTO.from(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BugResponseDTO criar(@Valid @RequestBody BugRequestDTO dto) {
        return BugResponseDTO.from(service.criar(dto));
    }

    @PutMapping("/{id}")
    public BugResponseDTO atualizar(@PathVariable(name="id") Long id, @Valid @RequestBody BugRequestDTO dto) {
        return BugResponseDTO.from(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable(name="id") Long id) {
        service.excluir(id);
    }
}
