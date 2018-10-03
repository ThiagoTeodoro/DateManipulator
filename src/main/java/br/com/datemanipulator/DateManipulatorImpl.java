package br.com.datemanipulator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * Projeto de classe respons�vel por fornecer m�todos para manipula��o de datas
 * de maneira geral.
 * 
 * @author Thiago Teodoro Rodrigues
 * @version 1.0
 *
 */
public class DateManipulatorImpl implements IDateManipulator {

	/**
	 * M�todo respons�vel por entregar a data de hoje.
	 * 
	 * @return
	 */
	public Date todayDate() {

		try {

			return new Date();

		} catch (Exception e) {

			System.err.println("Erro ao tentar obter a data atual!");
			e.printStackTrace();
			return null;

		}

	}

	/**
	 * M�todo por converter um tipo Date em uma String conforme pattern enviado.
	 * 
	 * @param date
	 *            Data para ser convertida em String
	 * @param pattern
	 *            Pattern da data que desejar. Ex: yyyy-MM-dd
	 * @return
	 */
	public String dateToString(Date date, DatePatterns pattern) {

		try {

			Date dataConverter = date;
			SimpleDateFormat formatador = new SimpleDateFormat(pattern.pattern);

			return formatador.format(dataConverter);

		} catch (Exception e) {

			System.err.println("Erro ao tentar converter a Data enviada para o Pattern desejado.");
			e.printStackTrace();
			return "";

		}

	}

	/**
	 * M�todo respons�vel por transformar uma data tipo String em uma data tipo
	 * Date.
	 * 
	 * @param data
	 *            Data tipo String
	 * @param patternData
	 *            Pattern que data tipo String se encontra
	 * @return
	 */
	public Date stringToDate(String data, DatePatterns patternData) {

		try {

			String dataConverter = data;
			SimpleDateFormat formato = new SimpleDateFormat(patternData.pattern);

			return formato.parse(dataConverter);

		} catch (Exception e) {

			System.err.println("Erro ao tentar converter a String enviada para Date!");
			e.printStackTrace();
			return null;

		}

	}

	/**
	 * M�todo respons�vel por converter um tipo Date em uma String conforme pattern
	 * enviado, esse m�todo permite a passagem de uma string pattern customizavel
	 * como par�metro.
	 * 
	 * @param date
	 *            Data para ser convertida em String
	 * @param pattern
	 *            Pattern da data que desejar. Ex: yyyy-MM-dd
	 * @return
	 */
	public String dateToString(Date date, String pattern) {

		try {

			Date dataConverter = date;
			SimpleDateFormat formatador = new SimpleDateFormat(pattern);

			return formatador.format(dataConverter);

		} catch (Exception e) {

			System.err.println("Erro ao tentar converter a Data enviada para o Pattern desejado.");
			e.printStackTrace();
			return "";

		}

	}

	/**
	 * M�todo respons�vel por transformar uma data tipo String em uma data tipo
	 * Date, esse m�todo permite a passgem de um String customizavem como pattern.
	 * 
	 * @param data
	 *            Data tipo String
	 * @param pattern
	 *            Pattern que data tipo String se encontra
	 * @return
	 */
	public Date stringToDate(String data, String pattern) {

		try {

			String dataConverter = data;
			SimpleDateFormat formato = new SimpleDateFormat(pattern);

			return formato.parse(dataConverter);

		} catch (Exception e) {

			System.err.println("Erro ao tentar converter a String enviada para Date!");
			e.printStackTrace();
			return null;

		}

	}

	/**
	 * M�todo respons�vel por entregar a data de hoje.
	 * 
	 * @return
	 */
	public Calendar todayCalendar() {

		try {

			return new GregorianCalendar();

		} catch (Exception e) {

			System.err.println("Erro ao tentar obter a data atual!");
			e.printStackTrace();
			return null;

		}

	}

	/**
	 * M�todo respons�vel por converter um date em um Calendar.
	 * 
	 * @return
	 */
	public Calendar dateToCalendar(Date date) {

		try {
			
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			return calendar;

		} catch (Exception e) {

			System.err.println("Erro ao tentar obter a data atual!");
			e.printStackTrace();
			return null;

		}

	}

	/**
	 * M�todo respons�vel por adicionar dias �teis conforme parametro a uma data,
	 * como dia util a fun��o entendi dias que n�o s�o feriados e n�o sabados e nem
	 * domingos.
	 * 
	 * Caso seja passado 0 como parametro de numero de dias para adicionar a fun��o
	 * ir� verificar se a data enviada � dia util e se n�o for vai adicionar dias
	 * at� o proximo dia util aparecer, se j� for dia util ela n�o far� nenhuma
	 * adi��o.
	 * 
	 * A lista de de feriados � importante para defini��o dos casos especias de dias
	 * uteis.
	 * 
	 * @param data
	 *            data a ser checada/adicionada
	 * @param numberOfDayAdd
	 *            numero de dias UTEIS que ser� adicionado o par�metro 0 apenas
	 *            verifica a data enviada se a mesma � �til ou n�o.
	 * @param holidays
	 *            lista de feriados para levar em considera��o
	 * @return
	 */
	public Date dayUtilAdd(Date data, int numberOfDayAdd, List<Date> holidays) {

		System.out.println(
				String.format("dayUtilAdd parametros entrada data[%s] - numberOfDayAdd[%s]", data, numberOfDayAdd));

		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(data);

		cal.add(Calendar.DAY_OF_MONTH, numberOfDayAdd);

		boolean isUtilDay = false;

		while (!isUtilDay) {

			int ds = cal.get(Calendar.DAY_OF_WEEK);

			// Verificando se o resultado n�o � sabado e nem Domingo.
			if (ds == Calendar.SATURDAY || ds == Calendar.SUNDAY) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
			} else {

				String calString = this.dateToString(cal.getTime(), DatePatterns.BR);
				String holiday = "";

				// Verificando se o resultado n�o � nenhum dos feriados na lista
				for (int i = 0; i < holidays.size(); i++) {

					holiday = this.dateToString(holidays.get(i), DatePatterns.BR);

					if (calString.equals(holiday)) {

						System.out.println("A data enviada � um feriado! Adicionando 1 dia.");

						cal.add(Calendar.DAY_OF_MONTH, 1);

					}

				}

				isUtilDay = true;

			}

		}

		System.out.println(String.format("Retorno da fun��o data [%s]", cal.getTime()));

		return cal.getTime();

	}

	// Supress�o do Metodo Anterior para casos em que ele n�o passar a lista de
	// feriados
	public Date dayUtilAdd(Date data, int numberOfDayAdd) {
		return dayUtilAdd(data, numberOfDayAdd, new ArrayList<Date>());
	}

	/**
	 * Metodo respons�vel por verificar se uma data est� vencida ou n�o.
	 * 
	 * O metodo retorna true caso a conta esteja vencida, vencida
	 * seguinifica que a dataVencimento � menor que que a dataReferencia
	 * se data for igual ele assume que n�o venceu pois no dia n�o venceu
	 * considera-se vencido apenas quando passa do dia de vencimento
	 *
	 * @param dataReferencia
	 *            a data que vamos usar para comparar se estar vencido ou n�o,
	 *            geralmente passamos a data atual (now).
	 * @param dataVencimento
	 *            data que irar vencer.
	 * @return
	 */
	public boolean verificaVencimento(Date dataReferencia, Date dataVencimento) {

		
		/*
		 * O metodo n�o pode levar em considera��o a hora apenas o dia
		 * tanto para referencia quanto para o vencimento
		 * limpado as horas atraves da formata��o de datas
		 */
		String dataReferenciaString = dateToString(dataReferencia, DatePatterns.BR);
		String dataVencimentoString = dateToString(dataVencimento, DatePatterns.BR);		
		
		
		Date referencia = stringToDate(dataReferenciaString, DatePatterns.BR);
		Date vencimento = stringToDate(dataVencimentoString, DatePatterns.BR);
		
		boolean data;

		if (referencia.before(vencimento)) {

			data = false;

		} else if (referencia.after(vencimento)) {

			data = true;

		} else {

			data = false;

		}

		return data;

	}

}
