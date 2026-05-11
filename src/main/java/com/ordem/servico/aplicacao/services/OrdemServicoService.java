package com.ordem.servico.aplicacao.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ordem.servico.apresentacao.dtos.OrdemServicoCreateDTO;
import com.ordem.servico.dominio.entidades.OrdemServico;
import com.ordem.servico.dominio.entidades.StatusOS;
import com.ordem.servico.dominio.entidades.Tecnico;
import com.ordem.servico.dominio.entidades.Solicitante;
import com.ordem.servico.dominio.entidades.Departamento;
import com.ordem.servico.infraestrutura.repositorios.OrdemServicoRepositorio;
import com.ordem.servico.infraestrutura.repositorios.TecnicoRepositorio;
import com.ordem.servico.infraestrutura.repositorios.SolicitanteRepositorio;
import com.ordem.servico.infraestrutura.repositorios.DepartamentoRepositorio;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepositorio ordemServicoRepositorio;
    private final TecnicoRepositorio tecnicoRepositorio;
    private final SolicitanteRepositorio solicitanteRepositorio;
    private final DepartamentoRepositorio departamentoRepositorio;

    public OrdemServicoService(
            OrdemServicoRepositorio ordemServicoRepositorio,
            TecnicoRepositorio tecnicoRepositorio,
            SolicitanteRepositorio solicitanteRepositorio,
            DepartamentoRepositorio departamentoRepositorio) {
        this.ordemServicoRepositorio = ordemServicoRepositorio;
        this.tecnicoRepositorio = tecnicoRepositorio;
        this.solicitanteRepositorio = solicitanteRepositorio;
        this.departamentoRepositorio = departamentoRepositorio;
    }

    public List<OrdemServico> listarTodas() {
        return ordemServicoRepositorio.findAll();
    }

    public OrdemServico buscarPorId(Long id) {
        return ordemServicoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com id: " + id));
    }

    public OrdemServico criar(OrdemServicoCreateDTO dto) {
        OrdemServico os = new OrdemServico();
        os.setDescricaoProblema(dto.getDescricaoProblema());
        os.setPrioridade(dto.getPrioridade() != null ? dto.getPrioridade() : "BAIXA");
        os.setDataAbertura(LocalDateTime.now());
        os.setStatus(StatusOS.ABERTA);
        os.setDataInicial(null);
        os.setDataFinalizacao(null);

        Solicitante solicitante = solicitanteRepositorio.findById(dto.getSolicitanteId().intValue())
                .orElseThrow(() -> new RuntimeException("Solicitante não encontrado com id: " + dto.getSolicitanteId()));
        os.setSolicitante(solicitante);

        Departamento departamento = departamentoRepositorio.findById(dto.getDepartamentoId())
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado com id: " + dto.getDepartamentoId()));
        os.setDepartamento(departamento);

        if (dto.getTecnicoId() != null) {
            Tecnico tecnico = tecnicoRepositorio.findById(dto.getTecnicoId())
                    .orElseThrow(() -> new RuntimeException("Tecnico não encontrado com id: " + dto.getTecnicoId()));
            os.setTecnico(tecnico);
        }

        return ordemServicoRepositorio.save(os);
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