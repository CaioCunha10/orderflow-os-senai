const API_BASE_URL = window.location.origin;

document.addEventListener('DOMContentLoaded', () => {
    init();
});

function init() {
    setupEventListeners();
    carregarListasAuxiliares();
    carregarOrdens();
    iniciarRelogio();
    
    // Set default date for new OS
    const now = new Date();
    now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
    document.getElementById('abertura').value = now.toISOString().slice(0, 16);
}

function setupEventListeners() {
    // Menu navigation
    document.querySelectorAll('.menu a').forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            const page = link.getAttribute('data-page');
            if (page === 'nova-os') abrirDrawer('drawerNovaOS');
            else if (page === 'executar') abrirDrawer('drawerExecutar');
            else if (page === 'analytics') abrirDrawer('drawerAnalytics');
            
            document.querySelectorAll('.menu a').forEach(a => a.classList.remove('active'));
            link.classList.add('active');
        });
    });

    // Drawers close
    document.getElementById('btnCloseNovaOS').addEventListener('click', () => fecharDrawer('drawerNovaOS'));
    document.getElementById('btnCloseExecutar').addEventListener('click', () => fecharDrawer('drawerExecutar'));
    document.getElementById('btnCloseAnalytics').addEventListener('click', () => fecharDrawer('drawerAnalytics'));

    // Create OS
    document.getElementById('btnCriarOS').addEventListener('click', criarOrdem);

    // Filters
    document.getElementById('btnFiltros').addEventListener('click', () => {
        const box = document.getElementById('filtersBox');
        box.classList.toggle('active');
    });

    // Tabs
    document.querySelectorAll('.tab').forEach(tab => {
        tab.addEventListener('click', () => {
            document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
            tab.classList.add('active');
            carregarOrdens(tab.getAttribute('data-status'));
        });
    });
}

function abrirDrawer(id) {
    document.getElementById(id).classList.add('active');
}

function fecharDrawer(id) {
    document.getElementById(id).classList.remove('active');
}

async function carregarListasAuxiliares() {
    try {
        const [tecnicos, solicitantes, departamentos] = await Promise.all([
            fetch(`${API_BASE_URL}/tecnicos`).then(res => res.json()),
            fetch(`${API_BASE_URL}/solicitantes`).then(res => res.json()),
            fetch(`${API_BASE_URL}/departamentos`).then(res => res.json())
        ]);

        populateSelect('solicitante', solicitantes, 'id_solicitante', 'nome');
        populateSelect('departamento', departamentos, 'id', 'nome');
        populateSelect('tecnico', tecnicos, 'id_tecnico', 'nome');
        
        populateSelect('filtroDepartamento', departamentos, 'id', 'nome');
        populateSelect('filtroTecnico', tecnicos, 'id_tecnico', 'nome');

    } catch (error) {
        console.error('Erro ao carregar listas auxiliares:', error);
    }
}

function populateSelect(id, items, valueField, textField) {
    const select = document.getElementById(id);
    if (!select) return;
    
    // Keep first option
    const firstOption = select.options[0];
    select.innerHTML = '';
    select.appendChild(firstOption);
    
    items.forEach(item => {
        const opt = document.createElement('option');
        opt.value = item[valueField];
        opt.textContent = item[textField];
        select.appendChild(opt);
    });
}

async function carregarOrdens(statusFiltro = '') {
    try {
        const res = await fetch(`${API_BASE_URL}/ordens`);
        let ordens = await res.json();

        if (statusFiltro) {
            ordens = ordens.filter(o => o.status === statusFiltro);
        }

        renderizarTabela(ordens);
        atualizarContadores(ordens);
    } catch (error) {
        showToast('Erro ao carregar ordens de serviço', 'error');
    }
}

function renderizarTabela(ordens) {
    const tbody = document.getElementById('tbodyOS');
    const emptyState = document.getElementById('emptyState');
    
    tbody.innerHTML = '';
    
    if (ordens.length === 0) {
        emptyState.style.display = 'block';
        return;
    }
    
    emptyState.style.display = 'none';
    
    ordens.forEach(os => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>#${os.id_os}</td>
            <td>${os.solicitante.nome}</td>
            <td>${os.departamento.nome}</td>
            <td>${formatarData(os.dataAbertura)}</td>
            <td>${formatarData(os.dataInicial)}</td>
            <td>${formatarData(os.dataFinalizacao)}</td>
            <td>${os.tecnico ? os.tecnico.nome : '<em>Não atribuído</em>'}</td>
            <td><span class="badge badge-${os.status.toLowerCase()}">${os.status}</span></td>
            <td>${os.descricaoProblema}</td>
            <td>${os.descricaoTecnica || '-'}</td>
            <td>
                <div class="actions">
                    <button class="btn-action" onclick="iniciarOS(${os.id_os})" ${os.status !== 'ABERTA' ? 'disabled' : ''} title="Iniciar">▶️</button>
                    <button class="btn-action" onclick="prepararFinalizarOS(${os.id_os})" ${os.status !== 'EM_ANDAMENTO' ? 'disabled' : ''} title="Finalizar">✔️</button>
                </div>
            </td>
        `;
        tbody.appendChild(tr);
    });

    document.getElementById('resultadosMostrados').textContent = ordens.length;
    document.getElementById('resultadosTotais').textContent = ordens.length;
}

async function criarOrdem() {
    const dto = {
        descricaoProblema: document.getElementById('descricaoProblema').value,
        prioridade: 'MEDIA',
        solicitanteId: parseInt(document.getElementById('solicitante').value),
        departamentoId: parseInt(document.getElementById('departamento').value),
        tecnicoId: document.getElementById('tecnico').value ? parseInt(document.getElementById('tecnico').value) : null
    };

    if (!dto.descricaoProblema || !dto.solicitanteId || !dto.departamentoId) {
        showToast('Por favor, preencha todos os campos obrigatórios.', 'error');
        return;
    }

    try {
        const res = await fetch(`${API_BASE_URL}/ordens`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dto)
        });

        if (res.ok) {
            showToast('Ordem de serviço criada com sucesso!');
            fecharDrawer('drawerNovaOS');
            carregarOrdens();
            limparCamposNovaOS();
        } else {
            const err = await res.text();
            showToast('Erro: ' + err, 'error');
        }
    } catch (error) {
        showToast('Erro ao conectar com o servidor', 'error');
    }
}

function limparCamposNovaOS() {
    document.getElementById('descricaoProblema').value = '';
    document.getElementById('solicitante').value = '';
    document.getElementById('departamento').value = '';
    document.getElementById('tecnico').value = '';
}

async function iniciarOS(id) {
    try {
        const res = await fetch(`${API_BASE_URL}/ordens/${id}/iniciar`, { method: 'PATCH' });
        if (res.ok) {
            showToast('OS iniciada!');
            carregarOrdens();
        }
    } catch (error) {
        showToast('Erro ao iniciar OS', 'error');
    }
}

async function prepararFinalizarOS(id) {
    const descricao = prompt('Informe a descrição técnica do serviço:');
    if (descricao === null) return;
    if (!descricao.trim()) {
        showToast('A descrição técnica é obrigatória para finalizar.', 'error');
        return;
    }

    try {
        const res = await fetch(`${API_BASE_URL}/ordens/${id}/finalizar`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ descricaoTecnica: descricao })
        });
        if (res.ok) {
            showToast('OS finalizada com sucesso!');
            carregarOrdens();
        } else {
            const err = await res.text();
            showToast('Erro ao finalizar: ' + err, 'error');
        }
    } catch (error) {
        showToast('Erro ao conectar com o servidor', 'error');
    }
}

function atualizarContadores(ordens) {
    document.getElementById('s-total').textContent = ordens.length;
    document.getElementById('s-open').textContent = ordens.filter(o => o.status === 'ABERTA').length;
    document.getElementById('s-wip').textContent = ordens.filter(o => o.status === 'EM_ANDAMENTO').length;
    document.getElementById('s-done').textContent = ordens.filter(o => o.status === 'FINALIZADA').length;
}

function formatarData(dataStr) {
    if (!dataStr) return '-';
    const d = new Date(dataStr);
    return d.toLocaleString('pt-BR');
}

function iniciarRelogio() {
    const clock = document.getElementById('clock');
    if (!clock) return;
    setInterval(() => {
        clock.textContent = new Date().toLocaleString('pt-BR');
    }, 1000);
}

function showToast(msg, type = 'success') {
    const area = document.getElementById('toastArea');
    const toast = document.createElement('div');
    toast.className = `toast toast-${type}`;
    toast.textContent = msg;
    area.appendChild(toast);
    setTimeout(() => toast.remove(), 4000);
}