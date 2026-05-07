package com.ordem.servico.dominio.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Ordem_Servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_os;

    @Column(name = "dt_abertura", updatable = false)
    private LocalDateTime dataAbertura = LocalDateTime.now();

    @Column(name = "dt_inicio")
    private LocalDateTime dataInicial;

    @Column(name = "dt_fim")
    private LocalDateTime dataFinalizacao;

    @Column(name = "desc_problema", nullable = false, length = 150)
    private String descricaoProblema;

    @Column(name = "desc_servico", length = 150)
    private String descricaoTecnica;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_os")
    private StatusOS status = StatusOS.ABERTA;

    @Column(nullable = false, length = 15)
    private String prioridade = "BAIXA";

    @ManyToOne
    @JoinColumn(name = "id_solicitante", nullable = false)
    private Solicitante solicitante;

    @ManyToOne
    @JoinColumn(name = "id_tecnico", nullable = false)
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "id_dept", nullable = false)
    private Departamento departamento;

    public OrdemServico() {
    }

    public Long getId_os() {
        return id_os;
    }

    public void setId_os(Long id_os) {
        this.id_os = id_os;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public String getDescricaoTecnica() {
        return descricaoTecnica;
    }

    public void setDescricaoTecnica(String descricaoTecnica) {
        this.descricaoTecnica = descricaoTecnica;
    }

    public StatusOS getStatus() {
        return status;
    }

    public void setStatus(StatusOS status) {
        this.status = status;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void finalizar(String descricaoTecnica) {
        if (this.status != StatusOS.EM_ANDAMENTO) {
            throw new IllegalStateException("A ordem de serviço só pode ser finalizada se estiver em andamento.");
        }

        validarDescricaoTecnica(descricaoTecnica);

        this.descricaoTecnica = descricaoTecnica;
        this.dataFinalizacao = LocalDateTime.now();
        this.status = StatusOS.FINALIZADA;
    }

    private void validarDescricaoTecnica(String descricaoTecnica) {
        if (descricaoTecnica == null || descricaoTecnica.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição técnica é obrigatória para finalizar a ordem de serviço.");
        }
    }

    public void iniciar() {
        if (this.status != StatusOS.ABERTA) {
            throw new IllegalStateException("A OS precisa estar ABERTA para iniciar");
        }

        this.status = StatusOS.EM_ANDAMENTO;
        this.dataInicial = LocalDateTime.now();
    }
}