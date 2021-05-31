function desabilitar(valor) {
    if (valor == 'sim') {
        document.getElementById('dias_abono').disabled = false;
    } else {
        document.getElementById('dias_abono').disabled = true;
    }
}