package com.ordem.servico.dominio.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ordem_de_servico")

public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataAbertura;
    private LocalDate dataInicial;
    private LocalDate dataFinalizacao;
    private String descricaoTecnica;

    @Enumerated(EnumType.STRING)
    private StatusOS status;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDataAbertura() 
    { return dataAbertura; }

    public void setDataAbertura(LocalDate dataAbertura) { this.dataAbertura = dataAbertura; }

    public LocalDate getDataInicio() 
    { return dataInicial; }

    public void setDataInicio(LocalDate dataInicial) { this.dataInicial = dataInicial; }

    public LocalDate getDataFinalizacao() 
    { return dataFinalizacao; }

    public void setDataFinalizacao(LocalDate dataFinalizacao) { this.dataFinalizacao = dataFinalizacao; }

    public String getDescricaoTecnica() 
    { return descricaoTecnica; }

    public void setDescricaoTecnica(String descricaoTecnica) { this.descricaoTecnica = descricaoTecnica; }

    public StatusOS getStatus() 
    { return status; }

    public void setStatus(StatusOS status) { this.status = status; }
}