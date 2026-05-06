package com.ordem.servico.dominio.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Tecnico")
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tecnico;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(length = 60)
    private String especialidade;
    public Tecnico() {}
    public Long getId_tecnico() {
        return id_tecnico;
    }

    public void setId_tecnico(Long id_tecnico) {
        this.id_tecnico = id_tecnico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}