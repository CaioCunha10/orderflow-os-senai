# OrderFlow - Sistema de Ordens de Serviço

Projeto acadêmico desenvolvido no SENAI Maracanã com o objetivo de gerenciar Ordens de Serviço (OS) de manutenção.

## Objetivo

Centralizar o controle das manutenções realizadas nos setores da unidade, permitindo o registro completo do ciclo de vida de uma OS:
abertura, execução e finalização.

## Funcionalidades (MVP)

* Criar Ordem de Serviço
* Listar Ordens de Serviço
* Iniciar execução da OS (data/hora início)
* Finalizar OS (data/hora fim + descrição técnica)
* Controle de status (Aberta, Em andamento, Finalizada)

## Tecnologias

* Backend: Spring Boot
* Banco de Dados: MySQL ou PostgreSQL
* Frontend: A definir

## Padrões do Projeto

### Branch

Cada tarefa deve seguir o padrão:

Tarefa-numero

Exemplo:
Tarefa-12

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
```

Criar a branch:

```bash
git checkout -b Tarefa-12
```

Após finalizar:

```bash
git add .
git commit -m "12 - descrição da tarefa"
git push origin Tarefa-12
```

---

### Pull Request

Após subir a branch:

1. Acessar o repositório no GitHub
2. Ir em "Pull Requests"
3. Clicar em "New Pull Request"
4. Base: main
5. Compare: Tarefa-numero
6. Criar o Pull Request

Regras:

* Não fazer push direto na main
* Toda alteração deve ser via Pull Request
* A main só será atualizada após aprovação
* Sempre atualizar a main antes de iniciar uma nova tarefa

---
