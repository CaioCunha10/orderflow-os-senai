package com.ordem.servico.apresentacao.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ordem.servico.aplicacao.services.OrdemServicoService;
import com.ordem.servico.apresentacao.dtos.FinalizarOrdemServicoDTO;
import com.ordem.servico.apresentacao.dtos.OrdemServicoCreateDTO;
import com.ordem.servico.dominio.entidades.OrdemServico;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/ordens")
@CrossOrigin(origins = "*")
@Tag(name = "Ordens de Serviço", description = "Gerenciamento de Ordens de Serviço")
public class OrdemServicoControlador {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoControlador(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @GetMapping
    @Operation(summary = "Listar todas as OS", description = "Retorna a lista completa de ordens de serviço")
    public ResponseEntity<List<OrdemServico>> listarTodas() {
        return ResponseEntity.ok(ordemServicoService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar OS por ID")
    public ResponseEntity<OrdemServico> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ordemServicoService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Criar nova OS", description = "Cria uma nova Ordem de Serviço com status ABERTA")
    public ResponseEntity<?> criar(@RequestBody OrdemServicoCreateDTO dto) {
        try {
            OrdemServico criada = ordemServicoService.criar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(criada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/iniciar")
    @Operation(summary = "Iniciar OS", description = "Inicia o atendimento de uma OS com status ABERTA, alterando para EM_ANDAMENTO")
    public ResponseEntity<?> iniciar(@PathVariable Long id) {
        try {
            OrdemServico iniciada = ordemServicoService.iniciar(id);
            return ResponseEntity.ok(iniciada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/finalizar")
    @Operation(summary = "Finalizar OS", description = "Finaliza uma OS EM_ANDAMENTO com a descrição técnica do serviço realizado")
    public ResponseEntity<?> finalizar(
            @PathVariable Long id,
            @RequestBody FinalizarOrdemServicoDTO dto) {
        try {
            OrdemServico finalizada = ordemServicoService.finalizar(id, dto.getDescricaoTecnica());
            return ResponseEntity.ok(finalizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar OS")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody OrdemServico ordemServico) {
        try {
            return ResponseEntity.ok(ordemServicoService.atualizar(id, ordemServico));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar OS")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            ordemServicoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}