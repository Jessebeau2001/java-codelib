package com.jessebeau.codelib.lang;

import java.util.Map;

public final class Langs {
	private Langs() {
	} // hidden

	public static LangDictionary ofEntries(Map<String, Resolvable> entries, Resolver resolver) {
		return new LangDictionary(Map.copyOf(entries), resolver);
	}
}
