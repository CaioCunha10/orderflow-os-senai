package com.ordem.servico.apresentacao.controladores;

<<<<<<< HEAD
import com.ordem.servico.aplicacao.services.OrdemServicoService;
import com.ordem.servico.apresentacao.dtos.OrdemServicoCreateDTO;
import com.ordem.servico.apresentacao.dtos.OrdemServicoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
=======

import java.util.List;

import com.ordem.servico.dominio.entidades.OrdemServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ordem.servico.infraestrutura.repositorios.OrdemServicoRepositorio;
>>>>>>> origin/main

@RestController
@RequestMapping("/ordens")
public class OrdemServicoControlador {
<<<<<<< HEAD

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoControlador(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    public ResponseEntity<OrdemServicoDTO> criar(@RequestBody OrdemServicoCreateDTO dto) {
        OrdemServicoDTO ordemCriada = ordemServicoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordemCriada);
    }
}
=======
	
	@Autowired
	private OrdemServicoRepositorio repositorio;
	
	@GetMapping
	public List<OrdemServico> ListarTodas(){
		return repositorio.findAll();
	}
		@GetMapping("/{id}")
	    public ResponseEntity<OrdemServico> buscarPorId(@PathVariable Long id) {
	        return repositorio.findById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
		}
	}
	
>>>>>>> origin/main
