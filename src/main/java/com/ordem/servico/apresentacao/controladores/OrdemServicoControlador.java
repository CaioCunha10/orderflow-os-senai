package com.ordem.servico.apresentacao.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ordem.servico.aplicacao.services.OrdemServicoService;
import com.ordem.servico.dominio.entidades.OrdemServico;

@RestController
@RequestMapping("/ordens")
public class OrdemServicoControlador {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoControlador(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @GetMapping
    public List<OrdemServico> listarTodas() {
        return ordemServicoService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ordemServicoService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OrdemServico> criar(@RequestBody OrdemServico ordemServico) {
        return ResponseEntity.ok(ordemServicoService.criar(ordemServico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServico> atualizar(
            @PathVariable Long id,
            @RequestBody OrdemServico ordemServico) {

        try {
            return ResponseEntity.ok(
                    ordemServicoService.atualizar(id, ordemServico));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            ordemServicoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}