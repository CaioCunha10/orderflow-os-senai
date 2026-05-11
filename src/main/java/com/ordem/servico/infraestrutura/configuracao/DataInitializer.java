package com.ordem.servico.infraestrutura.configuracao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ordem.servico.dominio.entidades.Departamento;
import com.ordem.servico.dominio.entidades.Solicitante;
import com.ordem.servico.dominio.entidades.Tecnico;
import com.ordem.servico.infraestrutura.repositorios.DepartamentoRepositorio;
import com.ordem.servico.infraestrutura.repositorios.SolicitanteRepositorio;
import com.ordem.servico.infraestrutura.repositorios.TecnicoRepositorio;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            DepartamentoRepositorio deptRepo,
            SolicitanteRepositorio solicitanteRepo,
            TecnicoRepositorio tecnicoRepo) {
        return args -> {
            if (deptRepo.count() == 0) {
                Departamento ti = deptRepo.save(new Departamento(null, "TI - Tecnologia da Informação"));
                Departamento manutencao = deptRepo.save(new Departamento(null, "Manutenção Predial"));
                Departamento rh = deptRepo.save(new Departamento(null, "Recursos Humanos"));

                tecnicoRepo.save(createTecnico("João Silva", "Redes e Infraestrutura"));
                tecnicoRepo.save(createTecnico("Maria Oliveira", "Desenvolvimento de Software"));
                tecnicoRepo.save(createTecnico("Carlos Santos", "Elétrica"));

                solicitanteRepo.save(createSolicitante("Ana Costa", ti));
                solicitanteRepo.save(createSolicitante("Ricardo Mendes", rh));
            }
        };
    }

    private Tecnico createTecnico(String nome, String esp) {
        Tecnico t = new Tecnico();
        t.setNome(nome);
        t.setEspecialidade(esp);
        return t;
    }

    private Solicitante createSolicitante(String nome, Departamento dept) {
        Solicitante s = new Solicitante();
        s.setNome(nome);
        s.setDepartamento(dept);
        return s;
    }
}
