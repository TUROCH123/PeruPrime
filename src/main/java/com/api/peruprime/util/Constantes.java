package com.api.peruprime.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Constantes {

	public static final String MENSAJE2 = "message {}{}";
	public static final String TEXTO_VACIO = "";
	public static final String TIMEOUT = "Timeout";
	public static final String MENSAJE3 = "message {}{}{}";
	
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
}
