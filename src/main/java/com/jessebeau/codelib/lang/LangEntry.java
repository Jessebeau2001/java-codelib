package com.jessebeau.codelib.lang;

import java.util.List;
import java.util.Objects;

public class LangEntry implements Resolvable {
	private final String value;
	private final List<String> params;

	public LangEntry(String value) {
		this(value, List.of()); // List.of already exists on heap so might as well use it
	}

	public LangEntry(String value, List<String> params) {
		this.value = Objects.requireNonNull(value);
		this.params = Objects.requireNonNull(params);
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public List<String> getParams() {
		return this.params;
	}
}
