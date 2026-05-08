package com.ordem.servico.apresentacao.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ordem.servico.aplicacao.services.OrdemServicoService;
import com.ordem.servico.apresentacao.dtos.FinalizarOrdemServicoDTO;
import com.ordem.servico.apresentacao.dtos.OrdemServicoCreateDTO;
import com.ordem.servico.apresentacao.dtos.OrdemServicoDTO;
import com.ordem.servico.dominio.entidades.OrdemServico;

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

    @GetMapping
    public ResponseEntity<List<OrdemServico>> listarTodas() {
        return ResponseEntity.ok(ordemServicoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordemServicoService.buscarPorId(id));
    }

    @PutMapping("/{id}/iniciar")
    public ResponseEntity<OrdemServico> iniciar(@PathVariable Long id) {
        OrdemServico ordemServico = ordemServicoService.iniciar(id);
        return ResponseEntity.ok(ordemServico);
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<OrdemServico> finalizar(
            @PathVariable Long id,
            @RequestBody FinalizarOrdemServicoDTO dto
    ) {
        OrdemServico ordemServico = ordemServicoService.finalizar(id, dto.getDescricaoTecnica());
        return ResponseEntity.ok(ordemServico);
    }
}