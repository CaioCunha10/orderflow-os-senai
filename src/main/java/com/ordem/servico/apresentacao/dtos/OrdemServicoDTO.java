package com.ordem.servico.apresentacao.dtos;

import java.time.LocalDateTime;

public class OrdemServicoDTO {

    private Long id;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFinalizacao;
    private String descricaoTecnica;
    private String status;
    private String nomeSolicitante;
    private String nomeDepartamento;

    public OrdemServicoDTO() {
    }

    public OrdemServicoDTO(
            Long id,
            LocalDateTime dataAbertura,
            LocalDateTime dataInicio,
            LocalDateTime dataFinalizacao,
            String descricaoTecnica,
            String status,
            String nomeSolicitante,
            String nomeDepartamento
    ) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.dataInicio = dataInicio;
        this.dataFinalizacao = dataFinalizacao;
        this.descricaoTecnica = descricaoTecnica;
        this.status = status;
        this.nomeSolicitante = nomeSolicitante;
        this.nomeDepartamento = nomeDepartamento;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public String getDescricaoTecnica() {
        return descricaoTecnica;
    }

    public String getStatus() {
        return status;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public void setDescricaoTecnica(String descricaoTecnica) {
        this.descricaoTecnica = descricaoTecnica;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }
}