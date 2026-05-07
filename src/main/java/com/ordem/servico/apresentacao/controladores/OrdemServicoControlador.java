package com.ordem.servico.apresentacao.controladores;

import com.ordem.servico.aplicacao.services.OrdemServicoService;
import com.ordem.servico.apresentacao.dtos.OrdemServicoCreateDTO;
import com.ordem.servico.apresentacao.dtos.OrdemServicoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordens")
public class OrdemServicoControlador {

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