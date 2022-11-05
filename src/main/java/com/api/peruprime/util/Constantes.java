package com.api.peruprime.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.api.peruprime.exception.WSException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Constantes {
	private static final Logger logger = LoggerFactory.getLogger(Constantes.class);
	public static final String MENSAJE2 = "message {}{}";
	public static final String TEXTO_VACIO = "";
	public static final String TIMEOUT = "Timeout";
	public static final String MENSAJE3 = "message {}{}{}";
	public static final String MONTH = "MONTH";
	public static final String YEAR = "YEAR";
	public static final String MENSAJE_IDT1 = "Error de timeout en %s - %s";
	public static final String MENSAJE_IDT2 = "Error de disponibilidad en %s - %s";
	public static final String MENSAJE_IDT3 = "Error inesperado. Error: %s - %s";
	public static final String CODIGO_IDT3 = "-3";
	public static final String CODIGO_IDT2 = "-2";
	public static final String CODIGO_IDT1 = "-1";
	
	public Constantes() {
		// Do nothing because of X and Y.
	}

	public static String printPrettyJSONString(Object o) throws JsonProcessingException {
		return new ObjectMapper().setDateFormat(getLocalFormat())
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writerWithDefaultPrettyPrinter()
				.writeValueAsString(o);
	}

	public static DateFormat getLocalFormat() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		dateFormat.setTimeZone(TimeZone.getDefault());
		return dateFormat;
	}

	public static int agregarvalor(String id) {
		int idnew = 1;
		int idres = 0;
		if (id == null) {
			idres = idres + idnew;
		} else {
			logger.info(Constantes.MENSAJE2, "[id] ", id);
			idres = Integer.parseInt(id) + idnew;

		}
		logger.info(Constantes.MENSAJE2, "[idres] ", idres);
		return idres;
	}

	public static void capturarErrorWs(Exception e, String nombreComponente, String nombreMetodo) throws WSException {
		String error = (e + Constantes.TEXTO_VACIO);
		if (error.contains(Constantes.TIMEOUT)) {
			throw new WSException(Constantes.CODIGO_IDT1,
					String.format(Constantes.MENSAJE_IDT1, nombreComponente, nombreMetodo), e);
		} else {
			throw new WSException(Constantes.CODIGO_IDT2,
					String.format(Constantes.MENSAJE_IDT2, nombreComponente, nombreMetodo), e);
		}
	}

	public static String fechaVencimiento(int tiempo, Date fechaInscripcion) {
		String fechaVencimiento = "";
		String fechaIns = "";
		int mesAdd = 0;
		if (fechaInscripcion == null) {
			logger.info(Constantes.MENSAJE2, "[fechaInscripcion] ", "nulo");
		} else {
			int mes = fechaInscripcion.getMonth();
			switch (mes) {
			case 0:
				mesAdd = validarcaso(tiempo, mes, mesAdd);
				break;
			case 1:
				mesAdd = validarcaso(tiempo, mes, mesAdd);
				break;
			case 2:
				mesAdd = validarcaso(tiempo, mes, mesAdd);
				break;
			case 3:
				mesAdd = validarcaso(tiempo, mes, mesAdd);
				break;
			case 4:
				mesAdd = validarcaso(tiempo, mes, mesAdd);
				break;
			case 5:
				mesAdd = validarcaso(tiempo, mes, mesAdd);
				break;
			case 6:
				mesAdd = validarcaso(tiempo, mes, mesAdd);
				break;
			case 7:
				mesAdd = validarcaso(tiempo, mes, mesAdd);
				break;
			case 8:
				mesAdd = validarcaso(tiempo, mes, mesAdd);
				break;
			case 9:
				mesAdd = validarcaso(tiempo, mes, mesAdd);
				break;
			case 10:
				mesAdd = validarcaso(tiempo, mes, mesAdd);
				break;
			case 11:
				mesAdd = validarcaso(tiempo, mes, mesAdd);
				break;
			default:
				break;
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			fechaIns = dateFormat.format(fechaInscripcion);
			logger.info(Constantes.MENSAJE2, "[fechaIns] ", fechaIns);
			fechaVencimiento = validarFecha(mesAdd, fechaIns);

			logger.info(Constantes.MENSAJE2, "[fechaVencimiento] ", fechaVencimiento);
		}

		return fechaVencimiento;
	}

	public static String validarFecha(int mesAdd, String fechaIns) {
		String valor = "";
		int sise = fechaIns.length();
		switch (mesAdd) {
		case 0:
			valor = "01";
			break;
		case 1:
			valor = "02";
			break;
		case 2:
			valor = "03";
			break;
		case 3:
			valor = "04";
			break;
		case 4:
			valor = "05";
			break;
		case 5:
			valor = "06";
			break;
		case 6:
			valor = "07";
			break;
		case 7:
			valor = "08";
			break;
		case 8:
			valor = "09";
			break;
		case 9:
			valor = "10";
			break;
		case 10:
			valor = "11";
			break;
		case 11:
			valor = "12";
			break;
		default:
			break;
		}

		logger.info(Constantes.MENSAJE2, "[fechaIns.substring(0,5)] ", fechaIns.substring(0, 5));
		logger.info(Constantes.MENSAJE2, "[valor] ", valor);
		logger.info(Constantes.MENSAJE2, "[fechaIns.substring(7,sise)] ", fechaIns.substring(7, sise));
		return fechaIns.substring(0, 5) + valor + fechaIns.substring(7, sise);
	}

	public static int validarcaso(int tiempo, int mes, int mesAdd) {
		if (tiempo == 12) {
			mesAdd = mes;
		} else {
			mesAdd = mes + tiempo;
		}
		return mesAdd;
	}
}
