package com.jessebeau.codelib.types;

import java.util.HashMap;
import java.util.Map;

public enum ContentType {
	TEXT_PLAIN("text/plain"),
	JSON("application/json"),
	HTML("text/html");

	private static final Map<String, ContentType> MIME_TYPE_MAP = new HashMap<>();

	static {
		for (var value : values()) {
			MIME_TYPE_MAP.put(value.mimeType, value);
		}
	}

	private final String mimeType;

	ContentType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String mimeType() {
		return this.mimeType;
	}

	@Override
	public String toString() {
		return mimeType;
	}

	public static ContentType ofMimeType(String mimeType) {
		return MIME_TYPE_MAP.get(mimeType);
	}
}
