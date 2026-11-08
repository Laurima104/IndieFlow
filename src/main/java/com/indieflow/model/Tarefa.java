package com.indieflow.model;

import java.time.LocalDate;

import com.indieflow.enums.Prioridade;
import com.indieflow.enums.StatusTarefa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name="tarefa")
public class Tarefa {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false, length = 50)
    private String titulo;
    @Column (length = 250)
    private String descricao;
    @Enumerated (EnumType.STRING)
    @Column (nullable = false, length = 10)
    private Prioridade prioridade;
    @Column (nullable = false, length = 15)
    private StatusTarefa status;
    @Column (nullable = false)
    private LocalDate prazo;

    @ManyToOne
    @JoinColumn (name="membro_id")
    private Membro membro;
    @ManyToOne 
    @JoinColumn (name="projeto_id", nullable = false)
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
