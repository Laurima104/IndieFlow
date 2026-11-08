package com.indieflow.model;

import java.time.LocalDate;

import com.indieflow.enums.Prioridade;
import com.indieflow.enums.StatusTarefa;

public class Tarefa {
    private Long id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private StatusTarefa status;
    private LocalDate prazo;
    private Membro membro;
    private Projeto projeto;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Projeto getProjeto() {
        return projeto;
    }
}
