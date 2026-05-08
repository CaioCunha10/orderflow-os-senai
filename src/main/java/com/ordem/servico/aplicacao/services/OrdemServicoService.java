package com.ordem.servico.aplicacao.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ordem.servico.apresentacao.dtos.OrdemServicoCreateDTO;
import com.ordem.servico.apresentacao.dtos.OrdemServicoDTO;
import com.ordem.servico.dominio.entidades.OrdemServico;
import com.ordem.servico.infraestrutura.repositorios.OrdemServicoRepositorio;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepositorio ordemServicoRepositorio;

    public OrdemServicoService(OrdemServicoRepositorio ordemServicoRepositorio) {
        this.ordemServicoRepositorio = ordemServicoRepositorio;
    }

    public List<OrdemServico> listarTodas() {
        return ordemServicoRepositorio.findAll();
    }

    public OrdemServico buscarPorId(Long id) {
        return ordemServicoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada"));
    }

    public OrdemServico salvar(OrdemServico ordemServico) {
        return ordemServicoRepositorio.save(ordemServico);
    }

    public OrdemServicoDTO criar(OrdemServicoCreateDTO dto) {
        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setDescricaoProblema(dto.getDescricaoProblema());

        OrdemServico ordemSalva = ordemServicoRepositorio.save(ordemServico);

        return new OrdemServicoDTO(
                ordemSalva.getId_os(),
                ordemSalva.getDataAbertura(),
                ordemSalva.getDataInicial(),
                ordemSalva.getDataFinalizacao(),
                ordemSalva.getDescricaoTecnica(),
                ordemSalva.getStatus().name(),
                ordemSalva.getSolicitante() != null ? ordemSalva.getSolicitante().getNome() : null,
                ordemSalva.getDepartamento() != null ? ordemSalva.getDepartamento().getNome() : null
        );
    }

    public OrdemServico iniciar(Long id) {
        OrdemServico ordemServico = buscarPorId(id);
        ordemServico.iniciar();
        return ordemServicoRepositorio.save(ordemServico);
    }

    public OrdemServico finalizar(Long id, String descricaoTecnica) {
        OrdemServico ordemServico = buscarPorId(id);
        ordemServico.finalizar(descricaoTecnica);
        return ordemServicoRepositorio.save(ordemServico);
    }
}