# OrderFlow - Sistema de Ordens de Serviço

Projeto acadêmico desenvolvido no SENAI Maracanã com o objetivo de gerenciar Ordens de Serviço (OS) de manutenção.

## Objetivo

Centralizar o controle das manutenções realizadas nos setores da unidade, permitindo o registro completo do ciclo de vida de uma OS:
abertura, execução e finalização.

## Funcionalidades (MVP)

- Criar Ordem de Serviço
- Listar Ordens de Serviço
- Iniciar execução da OS (data/hora início)
- Finalizar OS (data/hora fim + descrição técnica)
- Controle de status (Aberta, Em andamento, Finalizada)

## Tecnologias

- Backend: Spring Boot
- Banco de Dados: MySQL ou PostgreSQL
- Frontend: A definir

## Padrões do Projeto

### Branch

Cada tarefa deve seguir o padrão:

feature/#numero-nome-da-tarefa

Exemplo:
feature/#12-criar-endpoint-ordens

---

### Commits

Padrão obrigatório:

numero - descrição da tarefa

Exemplo:
1 - criar endpoint ordens  
12 - finalizar os  

---

### Fluxo de trabalho

Antes de iniciar qualquer tarefa:

```bash
git checkout main
git pull origin main
