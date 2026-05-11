package com.ordem.servico.infraestrutura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ordem.servico.dominio.entidades.Tecnico;

@Repository
public interface TecnicoRepositorio extends JpaRepository<Tecnico, Long> {
}
