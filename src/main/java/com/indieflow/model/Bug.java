package com.indieflow.model;

import com.indieflow.enums.Gravidade;
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
@Table (name="bug")
public class Bug {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false, length = 25)
    private String titulo;
    @Column (length = 150)
    private String descricao;
    @Enumerated(EnumType.STRING)
    @Column (nullable = false, length = 20)
    private Gravidade gravidade;
    @Enumerated (EnumType.STRING)
    @Column (nullable = false, length = 20)
    private StatusTarefa status;
    
    @ManyToOne 
    @JoinColumn (name="id_projeto", nullable = false)
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

    public String getTitulo(){
        return titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setGravidade(Gravidade gravidade) {
        this.gravidade = gravidade;
    }

    public Gravidade getGravidade(){
        return gravidade;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Projeto getProjeto() {
        return projeto;
    }
}
