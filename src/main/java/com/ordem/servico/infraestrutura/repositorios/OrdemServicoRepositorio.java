package com.ordem.servico.infraestrutura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ordem.servico.dominio.entidades.OrdemServico;

public interface OrdemServicoRepositorio extends JpaRepository<OrdemServico, Long>{
}
