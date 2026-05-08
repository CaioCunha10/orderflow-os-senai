package com.ordem.servico.dominio.entidades;

import jakarta.persistence.Entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long id_dept;

    private String nome;
    
    @OneToMany(mappedBy = "departamento")
    private List<OrdemServico> ordensServico;

    public Departamento() {}

    public Departamento(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public List<OrdemServico> getOrdensServico() {
    	return ordensServico;
    }
    public void setOrdensServico(List<OrdemServico> ordensServico) {
    	this.ordensServico = ordensServico;
    }
}