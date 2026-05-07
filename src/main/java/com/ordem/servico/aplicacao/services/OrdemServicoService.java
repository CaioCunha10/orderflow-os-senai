package com.ordem.servico.aplicacao.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ordem.servico.dominio.entidades.OrdemServico;
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
	public OrdemServico iniciar(Long id) {

	    OrdemServico os = ordemServicoRepositorio.findById(id)
	            .orElseThrow(() -> new RuntimeException("OS não encontrada"));

	    if (os.getStatus() != StatusOS.ABERTA) {
	        throw new RuntimeException("A OS precisa estar ABERTA para iniciar");
	    }

	    os.setStatus(StatusOS.EM_ANDAMENTO);
	    os.setDataInicio(LocalDateTime.now());

	    return ordemServicoRepositorio.save(os);

	}

}
