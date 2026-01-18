package com.jessebeau.codelib.lang;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonObject;

public final class GsonLangLoader {
	private GsonLangLoader() { } // hidden
/*	public static LangDictionary fromJson(JsonObject json, Resolver resolver) {
		var entries = new HashMap<String, Resolvable>();
		for (var entry : json.entrySet()) {
			var key = entry.getKey();
			var obj = entry.getValue().getAsJsonObject();

			var value = obj.get("value").getAsString();

			if (obj.has("params")) {
				var jParams = obj.get("params").getAsJsonArray();
				var values = new String[jParams.size()];
				for (int i = 0; i < values.length; i++) {
					values[i] = jParams.get(i).getAsString();
				}
				entries.put(key, new LangEntry(value, List.of(values)));
			} else {
				entries.put(key, new LangEntry(value));
			}
		}

		return Langs.ofEntries(entries, resolver);
	}

	public static LangDictionary fromResource(String path, Resolver resolver) {
		try (var inputStream = Langs.class.getResourceAsStream(path)) {
			if (inputStream == null)
				throw new IllegalArgumentException("Resource '%s' cannot found".formatted(path));

			var gson = new GsonBuilder().create();
			var read = gson.fromJson(new InputStreamReader(inputStream), JsonObject.class);
			return Langs.fromJson(read, resolver);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load language resource '%s': ".formatted(path), e);
		}
	}*/
}
