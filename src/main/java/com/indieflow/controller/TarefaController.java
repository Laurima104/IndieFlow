package com.indieflow.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.indieflow.dto.tarefa.TarefaRequestDTO;
import com.indieflow.dto.tarefa.TarefaResponseDTO;
import com.indieflow.service.TarefaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService service;
    public TarefaController(TarefaService service) { this.service = service; }

    @GetMapping public List<TarefaResponseDTO> listar() { return service.listarTodos().stream().map(TarefaResponseDTO::from).toList(); }
    @GetMapping("/{id}") public TarefaResponseDTO buscar(@PathVariable Long id) { return TarefaResponseDTO.from(service.buscarPorId(id)); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public TarefaResponseDTO criar(@Valid @RequestBody TarefaRequestDTO dto) { return TarefaResponseDTO.from(service.criar(dto)); }
    @PutMapping("/{id}") public TarefaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody TarefaRequestDTO dto) { return TarefaResponseDTO.from(service.atualizar(id, dto)); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) { service.excluir(id); }
}
