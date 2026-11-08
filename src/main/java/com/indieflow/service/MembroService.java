package com.indieflow.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.indieflow.dto.membro.MembroRequestDTO;
import com.indieflow.model.Membro;
import com.indieflow.repository.MembroRepository;

@Service
@Transactional
public class MembroService {
    private final MembroRepository membros;

    public MembroService(MembroRepository membros) { this.membros = membros; }

    @Transactional(readOnly = true)
    public List<Membro> listarTodos() { return membros.findAll(); }

    @Transactional(readOnly = true)
    public Membro buscarPorId(Long id) {
        return membros.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Membro " + id + " não encontrado"));
    }

    public Membro criar(MembroRequestDTO dto) {
        validarEmailDuplicado(dto.email(), null);
        Membro membro = new Membro();
        aplicar(membro, dto);
        return membros.save(membro);
    }

    public Membro atualizar(Long id, MembroRequestDTO dto) {
        Membro membro = buscarPorId(id);
        validarEmailDuplicado(dto.email(), id);
        aplicar(membro, dto);
        return membros.save(membro);
    }

    public void excluir(Long id) { membros.delete(buscarPorId(id)); }

    private void validarEmailDuplicado(String email, Long id) {
        boolean duplicado = id == null ? membros.existsByEmailIgnoreCase(email) : membros.existsByEmailIgnoreCaseAndIdNot(email, id);
        if (duplicado) throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe membro cadastrado com esse e-mail");
    }

    private void aplicar(Membro membro, MembroRequestDTO dto) {
        membro.setNome(dto.nome().trim());
        membro.setEmail(dto.email().trim().toLowerCase());
        membro.setEspecialidade(dto.especialidade().trim());
    }
}
