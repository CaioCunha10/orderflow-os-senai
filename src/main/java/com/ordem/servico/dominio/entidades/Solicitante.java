package com.ordem.servico.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitantes")
public class Solicitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_solicitante;
    private Long id;

    @Column(nullable = false, length = 60)
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "id_dept", nullable = false)
    private Departamento departamento;

    public Solicitante() {
    }

    public Solicitante(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}