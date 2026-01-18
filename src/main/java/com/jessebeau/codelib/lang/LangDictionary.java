package com.jessebeau.codelib.lang;

import java.util.Map;
import java.util.Objects;

public class LangDictionary {
	private final Map<String, Resolvable> library;
	private final Resolver resolver;

	public LangDictionary(Map<String, Resolvable> mappings, Resolver resolver) {
		this.library = Objects.requireNonNull(mappings);
		this.resolver = Objects.requireNonNull(resolver);
	}

	// public void transScribeNulls(true/false) <-- consider this possibility in the future

	public String transcribe(String key, Object... args) {
		var entry = library.get(key);
		if (entry == null) return null;
		return resolver.resolve(entry, args);
	}
}
