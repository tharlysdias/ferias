package br.com.proway.senior.ferias.utils;

public class Validacao {

	/**
	 * Validar Saldo.
	 * 
	 * Verifica se o colaborador tem saldo suficiente para requerer as suas férias.
	 * 
	 * @param diasSolicitados De ferias solicitados pelo colaborador
	 * @param saldoColaborador saldo de ferias do colaborador
	 * @return boolean
	 */
	public static boolean validarSaldo(int diasSolicitados, int saldoColaborador) {
		if(saldoColaborador > 0 && diasSolicitados <= saldoColaborador) {
			return true;
		}
		return false;
	}
	
}