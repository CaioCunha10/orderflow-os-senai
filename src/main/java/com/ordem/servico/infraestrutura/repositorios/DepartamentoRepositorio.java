package com.ordem.servico.infraestrutura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ordem.servico.dominio.entidades.Departamento;

public interface DepartamentoRepositorio extends JpaRepository<Departamento, Long>{

}
