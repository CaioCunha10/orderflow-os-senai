package com.ordem.servico.dominio.entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "solicitantes")
public class Solicitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_solicitante;

    @Column(nullable = false, length = 60)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_dept", nullable = false)
    private Departamento departamento;

    @OneToMany(mappedBy = "solicitante")
    private List<OrdemServico> ordensServico;

    public Solicitante() {
    }

    public Integer getId_solicitante() {
        return id_solicitante;
    }

    public void setId_solicitante(Integer id_solicitante) {
        this.id_solicitante = id_solicitante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<OrdemServico> getOrdensServico() {
        return ordensServico;
    }

    public void setOrdensServico(List<OrdemServico> ordensServico) {
        this.ordensServico = ordensServico;
    }
}