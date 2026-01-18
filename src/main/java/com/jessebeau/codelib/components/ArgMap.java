package com.jessebeau.codelib.components;

import java.util.*;
import java.util.function.Consumer;

public class ArgMap implements Iterable<String> {
	private final Map<String, String> map = new HashMap<>();
	private final String separator;

	public ArgMap(String separator, String[] args) {
		this.separator = separator;
		addAll(args);
	}

	private void add(String arg) {
		Objects.requireNonNull(arg, "Argument cannot be null");
		var split = arg.split(separator);
		if (split.length == 2) {
			map.put(split[0], split[1]);
		} else {
			throw new IllegalArgumentException("Argument must be of format key=value");
		}
	}

	private void addAll(String[] args) {
		for (var arg : args) {
			add(arg);
		}
	}

	public boolean contains(String key) {
		return map.containsKey(key);
	}

	public String getString(String key) throws NoSuchElementException {
		var value = map.get(key);
		if (value == null) {
			throw new NoSuchElementException("There is no arg with key " + key);
		}
		return value;
	}

	public void ifPresent(String key, Consumer<String> run) {
		var value = map.get(key);
		if (value != null) {
			run.accept(value);
		}
	}

	public int getInt(String key) throws NoSuchElementException, NumberFormatException {
		var value = getString(key);
		return Integer.parseInt(value);
	}

	public boolean getBoolean(String key) throws NoSuchElementException {
		// TODO: Maybe add 0,1 support
		var value = getString(key);
		return Boolean.parseBoolean(value);
	}

	public long getLong(String key) throws NoSuchElementException, NumberFormatException {
		var value = getString(key);
		return Long.parseLong(value);
	}

	@Override
	public Iterator<String> iterator() {
		return map.keySet().iterator();
	}
}
