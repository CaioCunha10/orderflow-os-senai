package com.ordem.servico.aplicacao.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ordem.servico.dominio.entidades.OrdemServico;
import com.ordem.servico.dominio.entidades.StatusOS;
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

    public OrdemServico criar(OrdemServico ordemServico) {
        ordemServico.setDataAbertura(LocalDateTime.now());
        ordemServico.setStatus(StatusOS.ABERTA);
        ordemServico.setDataInicial(null);
        ordemServico.setDataFinalizacao(null);

        return ordemServicoRepositorio.save(ordemServico);
    }

    public OrdemServico atualizar(Long id, OrdemServico ordemAtualizada) {

        OrdemServico ordemExistente = buscarPorId(id);

        ordemExistente.setDescricaoProblema(ordemAtualizada.getDescricaoProblema());
        ordemExistente.setDescricaoTecnica(ordemAtualizada.getDescricaoTecnica());
        ordemExistente.setPrioridade(ordemAtualizada.getPrioridade());
        ordemExistente.setStatus(ordemAtualizada.getStatus());
        ordemExistente.setSolicitante(ordemAtualizada.getSolicitante());
        ordemExistente.setTecnico(ordemAtualizada.getTecnico());
        ordemExistente.setDepartamento(ordemAtualizada.getDepartamento());

        return ordemServicoRepositorio.save(ordemExistente);
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

    public void deletar(Long id) {
        OrdemServico ordemServico = buscarPorId(id);
        ordemServicoRepositorio.delete(ordemServico);
    }
}