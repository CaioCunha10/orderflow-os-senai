package com.ordem.servico.apresentacao.dtos;

public class FinalizarOrdemServicoDTO {

    private String descricaoTecnica;

    public FinalizarOrdemServicoDTO() {
    }

    public FinalizarOrdemServicoDTO(String descricaoTecnica) {
        this.descricaoTecnica = descricaoTecnica;
    }

    public String getDescricaoTecnica() {
        return descricaoTecnica;
    }

    public void setDescricaoTecnica(String descricaoTecnica) {
        this.descricaoTecnica = descricaoTecnica;
    }
}