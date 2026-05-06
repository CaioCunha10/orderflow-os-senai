package com.ordem.servico.infraestrutura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ordem.servico.dominio.entidades.Solicitante;

public interface SolicitanteRepositorio extends JpaRepository<Solicitante, Long>{

}
