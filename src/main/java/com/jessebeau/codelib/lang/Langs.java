package com.jessebeau.codelib.lang;

import java.util.List;
import java.util.Map;

public final class Langs {
	private Langs() {
	} // hidden

	public static LangDictionary ofEntries(Map<String, Resolvable> entries, Resolver resolver) {
		return new LangDictionary(Map.copyOf(entries), resolver);
	}

	public static void main(String[] args) {
		// var lang = Langs.fromResource("/lang/en-US.json", Resolver.createLoose());
		Map<String, Resolvable> mappings = Map.of(
				"example.hello", new LangEntry("Hello, world!"),
				"example.greet", new LangEntry("Hello {name}!", List.of("name"))
		);
		LangDictionary lang = Langs.ofEntries(mappings, Resolver.createStrict());
		System.out.println(lang.transcribe("example.hello"));
		System.out.println(lang.transcribe("example.greet", 67));
	}
}
