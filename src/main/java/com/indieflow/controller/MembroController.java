package com.indieflow.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.indieflow.dto.membro.MembroRequestDTO;
import com.indieflow.dto.membro.MembroResponseDTO;
import com.indieflow.service.MembroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/membros")
public class MembroController {
    private final MembroService service;

    public MembroController(MembroService service) {
        this.service = service;
    }

    @GetMapping
    public List<MembroResponseDTO> listar() {
        return service.listarTodos().stream().map(MembroResponseDTO::from).toList();
    }

    @GetMapping("/{id}")
    public MembroResponseDTO buscar(@PathVariable(name="id") Long id) {
        return MembroResponseDTO.from(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MembroResponseDTO criar(@Valid @RequestBody MembroRequestDTO dto) {
        return MembroResponseDTO.from(service.criar(dto));
    }

    @PutMapping("/{id}")
    public MembroResponseDTO atualizar(@PathVariable(name="id") Long id, @Valid @RequestBody MembroRequestDTO dto) {
        return MembroResponseDTO.from(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable(name="id") Long id) {
        service.excluir(id);
    }
}
