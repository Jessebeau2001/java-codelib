package com.jessebeau.codelib.lang;

@FunctionalInterface
public interface Resolver {
	String resolve(Resolvable localizable, Object... params);

	static Resolver createStrict() {
		return (entry, args) -> {
			if (args.length != entry.getParams().size()) {
				throw new IllegalArgumentException("Parameters do not match template for " + entry.getValue());
			}

			var result = entry.getValue();
			var params = entry.getParams();
			for (int i = 0; i < params.size(); i++) {
				var param = params.get(i);
				result = result.replace("{" + param + "}", args[i].toString());
			}
			return result;
		};
	}

	static Resolver createLoose() {
		return (entry, args) -> {
			var result = entry.getValue();
			var params = entry.getParams();

			int len = Math.min(args.length, params.size());
			for (int i = 0; i < len; i++) {
				if (args[i] != null) { // skip nulls
					result = result.replace("{" + params.get(i) + "}", args[i].toString());
				}
			}

			return result;
		};
	}
}
