package com.jessebeau.codelib.data.json;

/*
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// https://www.tutorialspoint.com/gson/gson_custom_adapters.htm
public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

	@Override
	public void write(JsonWriter writer, LocalDateTime value) throws IOException {
		if (value == null) {
			writer.nullValue();
		} else {
			writer.value(value.format(FORMATTER));
		}
	}

	@Override
	public LocalDateTime read(JsonReader reader) throws IOException {
		var read = reader.nextString();
		return LocalDateTime.parse(read, FORMATTER);
	}
}
*/
