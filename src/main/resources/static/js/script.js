require("dotenv").config();
const express = require("express");
const cors    = require("cors");
 
const app  = express();
const PORT = process.env.PORT || 3000;
 
app.use(cors());
app.use(express.json());
 
// ── CONEXÃO COM BANCO DE DADOS ────────────────────────────────
// TODO: substitua este bloco pelo driver do seu banco.
// Exemplo com PostgreSQL (pg):
//
// const { Pool } = require("pg");
// const db = new Pool({
//   host:     process.env.DB_HOST,
//   port:     Number(process.env.DB_PORT),
//   database: process.env.DB_NAME,
//   user:     process.env.DB_USER,
//   password: process.env.DB_PASS,
// });
//
// Exemplo com MySQL (mysql2):
//
// const mysql = require("mysql2/promise");
// const db = mysql.createPool({
//   host:     process.env.DB_HOST,
//   port:     Number(process.env.DB_PORT),
//   database: process.env.DB_NAME,
//   user:     process.env.DB_USER,
//   password: process.env.DB_PASS,
// });
 
const db = null; // Remova esta linha quando configurar o banco
 
// ── HELPER: gerador de código de OS ──────────────────────────
// Gera: OS-2026-XXXX
async function gerarCodigoOS() {
  // TODO: ajuste a query para o seu banco
  // Exemplo pg:
  //   const { rows } = await db.query("SELECT COUNT(*) FROM ordens_servico");
  //   const count = parseInt(rows[0].count, 10);
  //   return `OS-${new Date().getFullYear()}-${String(1001 + count).slice(-4)}`;
  const ano   = new Date().getFullYear();
  const count = 0; // TODO: buscar contagem real do banco
  return `OS-${ano}-${String(1001 + count).padStart(4, "0")}`;
}
 
// ── HELPER: validação de campos obrigatórios ──────────────────
function validarCampos(body, campos) {
  const faltando = campos.filter(c => !body[c] || String(body[c]).trim() === "");
  return faltando;
}
 
// ── HELPER: resposta de erro padronizada ──────────────────────
function erroResposta(res, status, mensagem, detalhes) {
  return res.status(status).json({ sucesso: false, mensagem, detalhes });
}
 
// ── HELPER: paginação ─────────────────────────────────────────
function parsePaginacao(query) {
  const pagina   = Math.max(1, parseInt(query.pagina,  10) || 1);
  const tamanho  = Math.min(100, Math.max(1, parseInt(query.tamanho, 10) || 10));
  const offset   = (pagina - 1) * tamanho;
  return { pagina, tamanho, offset };
}
 
// ============================================================
//  ROTAS DE ORDENS DE SERVIÇO
// ============================================================
 
// ── GET /api/os ───────────────────────────────────────────────
// Lista todas as OS com suporte a:
//   - paginação:  ?pagina=1&tamanho=10
//   - filtro:     ?status=ABERTA|EM_ANDAMENTO|CONCLUIDA
//   - busca:      ?busca=texto
//   - ordenação:  ?ordenar=abertura&direcao=desc
app.get("/api/os", async (req, res) => {
  try {
    const { pagina, tamanho, offset } = parsePaginacao(req.query);
    const { status, busca, ordenar = "abertura", direcao = "desc" } = req.query;
 
    // TODO: montar query dinâmica para o banco
    // Exemplo pg:
    //
    // let sql    = "SELECT * FROM ordens_servico WHERE 1=1";
    // let sqlTotal = "SELECT COUNT(*) FROM ordens_servico WHERE 1=1";
    // const params = [];
    //
    // if (status) {
    //   params.push(status);
    //   sql      += ` AND status = $${params.length}`;
    //   sqlTotal += ` AND status = $${params.length}`;
    // }
    // if (busca) {
    //   const termo = `%${busca}%`;
    //   params.push(termo);
    //   const idx = params.length;
    //   sql      += ` AND (id ILIKE $${idx} OR solicitante ILIKE $${idx} OR departamento ILIKE $${idx} OR tecnico ILIKE $${idx})`;
    //   sqlTotal += ` AND (id ILIKE $${idx} OR solicitante ILIKE $${idx} OR departamento ILIKE $${idx} OR tecnico ILIKE $${idx})`;
    // }
    //
    // const colunasPermitidas = ["abertura","solicitante","departamento","tecnico","status"];
    // const col = colunasPermitidas.includes(ordenar) ? ordenar : "abertura";
    // const dir = direcao === "asc" ? "ASC" : "DESC";
    //
    // const paramsTotal = [...params];
    // params.push(tamanho, offset);
    //
    // sql += ` ORDER BY ${col} ${dir} LIMIT $${params.length - 1} OFFSET $${params.length}`;
    //
    // const { rows: dados }    = await db.query(sql, params);
    // const { rows: totResult } = await db.query(sqlTotal, paramsTotal);
    // const total = parseInt(totResult[0].count, 10);
 
    // PLACEHOLDER (remova quando integrar o banco):
    const dados = [];
    const total = 0;
    const totalPaginas = Math.max(1, Math.ceil(total / tamanho));
 
    return res.json({
      sucesso: true,
      dados,
      paginacao: { pagina, tamanho, total, totalPaginas }
    });
  } catch (err) {
    console.error("[GET /api/os]", err);
    return erroResposta(res, 500, "Erro interno ao listar OS");
  }
});
 
// ── GET /api/os/:id ───────────────────────────────────────────
// Busca uma OS específica pelo ID
app.get("/api/os/:id", async (req, res) => {
  try {
    const { id } = req.params;
 
    // TODO: buscar no banco
    // Exemplo pg:
    // const { rows } = await db.query("SELECT * FROM ordens_servico WHERE id = $1", [id]);
    // if (rows.length === 0) return erroResposta(res, 404, "OS não encontrada");
    // return res.json({ sucesso: true, dados: rows[0] });
 
    // PLACEHOLDER:
    return erroResposta(res, 404, "OS não encontrada (banco não configurado)");
  } catch (err) {
    console.error("[GET /api/os/:id]", err);
    return erroResposta(res, 500, "Erro interno ao buscar OS");
  }
});
 
// ── POST /api/os ──────────────────────────────────────────────
// Cria uma nova OS
// Body esperado: { solicitante, departamento, tecnico, abertura, descricao, inicio?, fechamento?, servico? }
app.post("/api/os", async (req, res) => {
  try {
    const camposObrigatorios = ["solicitante", "departamento", "tecnico", "abertura", "descricao"];
    const faltando = validarCampos(req.body, camposObrigatorios);
    if (faltando.length > 0) {
      return erroResposta(res, 400, "Campos obrigatórios faltando", { campos: faltando });
    }
 
    const { solicitante, departamento, tecnico, abertura, descricao, inicio, fechamento, servico } = req.body;
    const id = await gerarCodigoOS();
 
    let status = "ABERTA";
    if (inicio && fechamento && servico) status = "CONCLUIDA";
    else if (inicio)                     status = "EM_ANDAMENTO";
 
    // TODO: inserir no banco
    // Exemplo pg:
    // await db.query(
    //   `INSERT INTO ordens_servico
    //    (id, solicitante, departamento, tecnico, abertura, inicio, fechamento, status, descricao, servico)
    //    VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10)`,
    //   [id, solicitante, departamento, tecnico, abertura, inicio||null, fechamento||null, status, descricao, servico||null]
    // );
 
    return res.status(201).json({
      sucesso: true,
      mensagem: `OS ${id} criada com sucesso`,
      dados: { id, solicitante, departamento, tecnico, abertura, inicio: inicio||"", fechamento: fechamento||"", status, descricao, servico: servico||"" }
    });
  } catch (err) {
    console.error("[POST /api/os]", err);
    return erroResposta(res, 500, "Erro interno ao criar OS");
  }
});
 
// ── PUT /api/os/:id ───────────────────────────────────────────
// Edita campos de uma OS existente (item 9)
// Body esperado: { solicitante?, departamento?, tecnico?, descricao?, servico? }
app.put("/api/os/:id", async (req, res) => {
  try {
    const { id } = req.params;
    const { solicitante, departamento, tecnico, descricao, servico } = req.body;
 
    // TODO: verificar se OS existe, então atualizar
    // Exemplo pg:
    // const { rows } = await db.query("SELECT * FROM ordens_servico WHERE id = $1", [id]);
    // if (rows.length === 0) return erroResposta(res, 404, "OS não encontrada");
    //
    // await db.query(
    //   `UPDATE ordens_servico SET
    //     solicitante  = COALESCE($1, solicitante),
    //     departamento = COALESCE($2, departamento),
    //     tecnico      = COALESCE($3, tecnico),
    //     descricao    = COALESCE($4, descricao),
    //     servico      = COALESCE($5, servico),
    //     updated_at   = NOW()
    //    WHERE id = $6`,
    //   [solicitante||null, departamento||null, tecnico||null, descricao||null, servico||null, id]
    // );
 
    return res.json({ sucesso: true, mensagem: `OS ${id} atualizada` });
  } catch (err) {
    console.error("[PUT /api/os/:id]", err);
    return erroResposta(res, 500, "Erro interno ao editar OS");
  }
});
 
// ── PATCH /api/os/:id/iniciar ─────────────────────────────────
// Registra início da OS (status → EM_ANDAMENTO)
app.patch("/api/os/:id/iniciar", async (req, res) => {
  try {
    const { id }    = req.params;
    const { inicio } = req.body; // ISO string ou formato BR
    const agora     = inicio || new Date().toLocaleString("pt-BR");
 
    // TODO: verificar status atual (deve ser ABERTA), então atualizar
    // Exemplo pg:
    // const { rows } = await db.query("SELECT status FROM ordens_servico WHERE id = $1", [id]);
    // if (rows.length === 0) return erroResposta(res, 404, "OS não encontrada");
    // if (rows[0].status !== "ABERTA") return erroResposta(res, 400, "OS não está em aberto");
    //
    // await db.query(
    //   "UPDATE ordens_servico SET status = 'EM_ANDAMENTO', inicio = $1, updated_at = NOW() WHERE id = $2",
    //   [agora, id]
    // );
 
    return res.json({ sucesso: true, mensagem: `OS ${id} iniciada`, dados: { id, status: "EM_ANDAMENTO", inicio: agora } });
  } catch (err) {
    console.error("[PATCH /api/os/:id/iniciar]", err);
    return erroResposta(res, 500, "Erro interno ao iniciar OS");
  }
});
 
// ── PATCH /api/os/:id/finalizar ──────────────────────────────
// Registra fechamento e conclui a OS (status → CONCLUIDA)
app.patch("/api/os/:id/finalizar", async (req, res) => {
  try {
    const { id }           = req.params;
    const { fechamento, servico } = req.body;
 
    if (!servico || String(servico).trim() === "") {
      return erroResposta(res, 400, "Descrição do serviço executado é obrigatória para concluir a OS");
    }
 
    const agora = fechamento || new Date().toLocaleString("pt-BR");
 
    // TODO: verificar status atual (deve ser EM_ANDAMENTO), então atualizar
    // Exemplo pg:
    // const { rows } = await db.query("SELECT status FROM ordens_servico WHERE id = $1", [id]);
    // if (rows.length === 0) return erroResposta(res, 404, "OS não encontrada");
    // if (rows[0].status !== "EM_ANDAMENTO") return erroResposta(res, 400, "OS não está em execução");
    //
    // await db.query(
    //   "UPDATE ordens_servico SET status = 'CONCLUIDA', fechamento = $1, servico = $2, updated_at = NOW() WHERE id = $3",
    //   [agora, servico.trim(), id]
    // );
 
    return res.json({ sucesso: true, mensagem: `OS ${id} finalizada`, dados: { id, status: "CONCLUIDA", fechamento: agora } });
  } catch (err) {
    console.error("[PATCH /api/os/:id/finalizar]", err);
    return erroResposta(res, 500, "Erro interno ao finalizar OS");
  }
});
 
// ── PATCH /api/os/:id/reiniciar ──────────────────────────────
// Reinicia uma OS concluída (status → ABERTA, limpa fechamento/servico)
app.patch("/api/os/:id/reiniciar", async (req, res) => {
  try {
    const { id }    = req.params;
    const { inicio } = req.body;
    const agora     = inicio || new Date().toLocaleString("pt-BR");
 
    // TODO: verificar status atual (deve ser CONCLUIDA), então atualizar
    // Exemplo pg:
    // const { rows } = await db.query("SELECT status FROM ordens_servico WHERE id = $1", [id]);
    // if (rows.length === 0) return erroResposta(res, 404, "OS não encontrada");
    // if (rows[0].status !== "CONCLUIDA") return erroResposta(res, 400, "Apenas OS concluídas podem ser reiniciadas");
    //
    // await db.query(
    //   "UPDATE ordens_servico SET status = 'ABERTA', inicio = $1, fechamento = NULL, servico = NULL, updated_at = NOW() WHERE id = $2",
    //   [agora, id]
    // );
 
    return res.json({ sucesso: true, mensagem: `OS ${id} reiniciada`, dados: { id, status: "ABERTA", inicio: agora } });
  } catch (err) {
    console.error("[PATCH /api/os/:id/reiniciar]", err);
    return erroResposta(res, 500, "Erro interno ao reiniciar OS");
  }
});
 
// ── DELETE /api/os/:id ────────────────────────────────────────
// Remove uma OS (use com cuidado)
app.delete("/api/os/:id", async (req, res) => {
  try {
    const { id } = req.params;
 
    // TODO: remover do banco
    // Exemplo pg:
    // const { rowCount } = await db.query("DELETE FROM ordens_servico WHERE id = $1", [id]);
    // if (rowCount === 0) return erroResposta(res, 404, "OS não encontrada");
 
    return res.json({ sucesso: true, mensagem: `OS ${id} removida` });
  } catch (err) {
    console.error("[DELETE /api/os/:id]", err);
    return erroResposta(res, 500, "Erro interno ao remover OS");
  }
});
 
// ── GET /api/analytics ────────────────────────────────────────
// Retorna indicadores de performance para a tela de relatórios
app.get("/api/analytics", async (req, res) => {
  try {
    // TODO: calcular no banco
    // Exemplo pg:
    //
    // const { rows: contagens } = await db.query(`
    //   SELECT
    //     COUNT(*) FILTER (WHERE status = 'ABERTA')       AS abertas,
    //     COUNT(*) FILTER (WHERE status = 'EM_ANDAMENTO') AS em_andamento,
    //     COUNT(*) FILTER (WHERE status = 'CONCLUIDA')    AS concluidas,
    //     COUNT(*)                                         AS total
    //   FROM ordens_servico
    // `);
    //
    // const { rows: tempoRows } = await db.query(`
    //   SELECT AVG(EXTRACT(EPOCH FROM (fechamento::timestamp - inicio::timestamp)) / 60) AS tempo_medio
    //   FROM ordens_servico
    //   WHERE status = 'CONCLUIDA' AND inicio IS NOT NULL AND fechamento IS NOT NULL
    // `);
    //
    // const { rows: porTecnico } = await db.query(`
    //   SELECT tecnico, COUNT(*) AS total
    //   FROM ordens_servico GROUP BY tecnico ORDER BY total DESC
    // `);
    //
    // const { rows: porDept } = await db.query(`
    //   SELECT departamento, COUNT(*) AS total
    //   FROM ordens_servico GROUP BY departamento ORDER BY total DESC
    // `);
 
    // PLACEHOLDER:
    const dados = {
      total: 0, abertas: 0, em_andamento: 0, concluidas: 0,
      taxa_conclusao: 0,
      tempo_medio_minutos: 0,
      por_tecnico: [],
      por_departamento: []
    };
 
    return res.json({ sucesso: true, dados });
  } catch (err) {
    console.error("[GET /api/analytics]", err);
    return erroResposta(res, 500, "Erro interno ao calcular analytics");
  }
});
 
// ── ROTA DE SAÚDE ─────────────────────────────────────────────
app.get("/api/health", (_req, res) => {
  res.json({ sucesso: true, versao: "1.0.0", timestamp: new Date().toISOString() });
});
 
// ── INICIAR SERVIDOR ──────────────────────────────────────────
app.listen(PORT, () => {
  console.log(`\n✅  FIRJAN SENAI — API rodando em http://localhost:${PORT}`);
  console.log(`   Ambiente: ${process.env.NODE_ENV || "development"}`);
  console.log(`   Banco:    ${db ? "conectado" : "NÃO configurado (configure em server.js)"}\n`);
});
 
module.exports = app;