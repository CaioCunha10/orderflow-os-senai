package com.ordem.servico.apresentacao.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ordem.servico.dominio.entidades.Departamento;
import com.ordem.servico.dominio.entidades.Solicitante;
import com.ordem.servico.dominio.entidades.Tecnico;
import com.ordem.servico.infraestrutura.repositorios.DepartamentoRepositorio;
import com.ordem.servico.infraestrutura.repositorios.SolicitanteRepositorio;
import com.ordem.servico.infraestrutura.repositorios.TecnicoRepositorio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Cadastros", description = "Listagem de Técnicos, Solicitantes e Departamentos")
public class CadastroControlador {

    private final TecnicoRepositorio tecnicoRepositorio;
    private final SolicitanteRepositorio solicitanteRepositorio;
    private final DepartamentoRepositorio departamentoRepositorio;

    public CadastroControlador(
            TecnicoRepositorio tecnicoRepositorio,
            SolicitanteRepositorio solicitanteRepositorio,
            DepartamentoRepositorio departamentoRepositorio) {
        this.tecnicoRepositorio = tecnicoRepositorio;
        this.solicitanteRepositorio = solicitanteRepositorio;
        this.departamentoRepositorio = departamentoRepositorio;
    }

    @GetMapping("/tecnicos")
    @Operation(summary = "Listar todos os técnicos")
    public ResponseEntity<List<Tecnico>> listarTecnicos() {
        return ResponseEntity.ok(tecnicoRepositorio.findAll());
    }

    @PostMapping("/tecnicos")
    @Operation(summary = "Cadastrar técnico")
    public ResponseEntity<Tecnico> criarTecnico(@RequestBody Tecnico tecnico) {
        return ResponseEntity.ok(tecnicoRepositorio.save(tecnico));
    }

    @GetMapping("/solicitantes")
    @Operation(summary = "Listar todos os solicitantes")
    public ResponseEntity<List<Solicitante>> listarSolicitantes() {
        return ResponseEntity.ok(solicitanteRepositorio.findAll());
    }

    @PostMapping("/solicitantes")
    @Operation(summary = "Cadastrar solicitante")
    public ResponseEntity<Solicitante> criarSolicitante(@RequestBody Solicitante solicitante) {
        return ResponseEntity.ok(solicitanteRepositorio.save(solicitante));
    }

    @GetMapping("/departamentos")
    @Operation(summary = "Listar todos os departamentos")
    public ResponseEntity<List<Departamento>> listarDepartamentos() {
        return ResponseEntity.ok(departamentoRepositorio.findAll());
    }

    @PostMapping("/departamentos")
    @Operation(summary = "Cadastrar departamento")
    public ResponseEntity<Departamento> criarDepartamento(@RequestBody Departamento departamento) {
        return ResponseEntity.ok(departamentoRepositorio.save(departamento));
    }
}
