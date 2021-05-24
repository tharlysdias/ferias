package br.com.proway.senior.ferias.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe utilizada para manipular e validar datas
 * 
 * @author David Hildebrandt <davihildebran@gmail.com>
 * @author Lucas Grijo <rksgrijo@gmail.com>
 */
public class Data {
	
	private static final int PRAZO_MINIMO_SOLICITACAO_FERIAS = 15;

	/**
	 * Retorna quantidade de dias
	 * 
	 * Retorna a quantidade de dias entre as datas informadas.
	 * 
	 * @param inicio  LocalDate
	 * @param termino LocalDate
	 * @return dias de intervalo entre as datas
	 */
	public static int retornarIntervaloEmDiasEntreAsDatas(LocalDate inicio, LocalDate termino) {
		int dias = (int) ChronoUnit.DAYS.between(inicio, termino);
		if (inicio.isBefore(termino)) {
			return dias;
		}
		return -1;
	}

	/**
	 * Valida o Prazo da Solicitacao de Ferias.
	 * 
	 * Verifica se o inicio de uma ferias se da ao menos
	 * PRAZO_MINIMO_SOLICITACAO_FERIAS dias apos a requisicao ser criada, para
	 * evitar que uma ferias seja pedida sem antecedencia.
	 * 
	 * @param dataInicioFerias
	 * @return true caso o requerimento seja criado com a antecedencia necessaria.
	 * 
	 */
	public static boolean validacaoPrazoSolicitacaoDeFerias(LocalDate dataCriacaoRequerimento, 
			LocalDate dataInicioFerias) {
		int intervalo = retornarIntervaloEmDiasEntreAsDatas(dataCriacaoRequerimento, dataInicioFerias);
		if (intervalo >= PRAZO_MINIMO_SOLICITACAO_FERIAS + 1) {
			return true;
		} else {
			return false;
		}
	}
	
}
