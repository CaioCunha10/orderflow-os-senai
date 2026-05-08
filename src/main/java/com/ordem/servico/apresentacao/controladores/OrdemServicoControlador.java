package com.ordem.servico.apresentacao.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ordem.servico.dominio.entidades.OrdemServico;
import com.ordem.servico.infraestrutura.repositorios.OrdemServicoRepositorio;

@RestController
@RequestMapping("/ordens")
public class OrdemServicoControlador {

    @Autowired
    private OrdemServicoRepositorio repositorio;

    @GetMapping
    public List<OrdemServico> listarOrdens() {
        return repositorio.findAll();
    }
}