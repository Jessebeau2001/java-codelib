package com.jessebeau.codelib.data;

import java.util.*;

public class MarkdownTableBuilder {
	// Linked map, keep insertion order
	private final Map<String, Column> columns = new LinkedHashMap<>();
	private int height = 0;

	public MarkdownTableBuilder add(Object column, Object value) {
		Objects.requireNonNull(column, "Cannot add to column null");
		Objects.requireNonNull(column, "Cannot add null value to column");
		return add(column.toString(), value.toString());
	}

	public MarkdownTableBuilder add(String columnName, String value) {
		Objects.requireNonNull(columnName, "Cannot add to column name null");
		var column = getColumn(columnName);
		column.add(value);
		if (column.height() > height) {
			height = column.height();
		}
		return this;
	}

	private Column getColumn(String title) {
		return columns.computeIfAbsent(title, Column::new);
	}

	private int height() {
		return height;
	}

	@Override
	public String toString() {
		var rows = new ArrayList<StringBuilder>();

		var header = new StringBuilder();
		var separator = new StringBuilder();
		for (var col : columns.values()) {
			var padded = pad(col.title(), col.width());
			header.append("| ").append(padded).append(" ");
			separator.append("|").repeat("-", padded.length() + 2);
		}
		rows.add(header.append("|"));
		rows.add(separator.append("|"));

		for (int row = 0; row < height(); row++) {
			var sb = new StringBuilder();
			for (var col : columns.values()) {
				// Throws if not square
				var padded = pad(col.values().get(row), col.width());
				sb.append("| ").append(padded).append(" ");
			}
			rows.add(sb.append("|"));
		}

		var table = new StringBuilder();
		for (var row : rows) {
			table.append(row).append(System.lineSeparator());
		}
		return table.toString();
	}

	private static String pad(String value, int size) {
		return String.format("%-" + size + "s", value);
	}

	private static class Column {
		private final String title;
		private final List<String> values = new LinkedList<>();
		private int longestValueLength = 0;

		public Column(String title) {
			this.title = title;
			updateWidth(title.length());
		}

		private void updateWidth(int width) {
			if (width > longestValueLength) {
				longestValueLength = width;
			}
		}

		void add(String value) {
			values.add(value);
			updateWidth(value.length());
		}

		String title() {
			return title;
		}

		List<String> values() {
			return values;
		}

		int width() {
			return longestValueLength;
		}

		int height() {
			return values.size();
		}

		@Override
		public String toString() {
			return title + " " + values + " (longest value: " + longestValueLength + ")";
		}
	}
}
