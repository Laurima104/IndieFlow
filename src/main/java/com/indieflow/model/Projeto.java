package com.indieflow.model;

import java.time.LocalDate;

import com.indieflow.enums.StatusProjeto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name="projeto")
public class Projeto {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false, length = 25)
    private String nome;
    @Column (length = 200)
    private String descricao;
    @Column (nullable = false, length = 20)
    private String genero;
    @Enumerated (EnumType.STRING)
    @Column (nullable = false, length = 20)
    private StatusProjeto status;
    @Column (nullable = false)
    private LocalDate data_inicio;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }
}
