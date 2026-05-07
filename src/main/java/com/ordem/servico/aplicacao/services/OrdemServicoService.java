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
	
	public List<OrdemServico> listarTodas(){
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

}
