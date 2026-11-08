package com.indieflow.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.indieflow.dto.projeto.ProjetoRequestDTO;
import com.indieflow.dto.projeto.ProjetoResponseDTO;
import com.indieflow.service.ProjetoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {
    private final ProjetoService service;
    public ProjetoController(ProjetoService service) { this.service = service; }

    @GetMapping public List<ProjetoResponseDTO> listar() { return service.listarTodos().stream().map(ProjetoResponseDTO::from).toList(); }
    @GetMapping("/{id}") public ProjetoResponseDTO buscar(@PathVariable Long id) { return ProjetoResponseDTO.from(service.buscarPorId(id)); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public ProjetoResponseDTO criar(@Valid @RequestBody ProjetoRequestDTO dto) { return ProjetoResponseDTO.from(service.criar(dto)); }
    @PutMapping("/{id}") public ProjetoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProjetoRequestDTO dto) { return ProjetoResponseDTO.from(service.atualizar(id, dto)); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) { service.excluir(id); }
}
