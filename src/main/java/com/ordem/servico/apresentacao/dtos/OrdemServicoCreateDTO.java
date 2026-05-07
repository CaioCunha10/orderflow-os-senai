package com.ordem.servico.apresentacao.dtos;

public class OrdemServicoCreateDTO {

    private Long solicitanteId;
    private Long departamentoId;

    public OrdemServicoCreateDTO() {
    }

    public Long getSolicitanteId() {
        return solicitanteId;
    }

    public void setSolicitanteId(Long solicitanteId) {
        this.solicitanteId = solicitanteId;
    }

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }
}