package com.jessebeau.codelib.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CSVParser<T> implements AutoCloseable {
	private final BufferedReader reader;
	private final ObjectMapper<T> mapper;
	private final String delimiter;
	private final int cols;
	private boolean skipNext;
	private int linesRead = 0;

	public CSVParser(Path path, ObjectMapper<T> mapper, String delimiter, int cols, boolean skipHeader) throws IOException {
		this(Files.newBufferedReader(path), mapper, delimiter, cols, skipHeader);
	}

	public CSVParser(InputStream stream, ObjectMapper<T> mapper, String delimiter, int cols, boolean skipHeader) {
		this(new BufferedReader(new InputStreamReader(stream)), mapper, delimiter, cols, skipHeader);
	}

	private CSVParser(BufferedReader reader, ObjectMapper<T> mapper, String delimiter, int cols, boolean skipHeader) {
		this.reader = reader;
		this.mapper = mapper;
		this.delimiter = delimiter;
		this.cols = cols;
		this.skipNext = skipHeader;
	}

	public T readNext() throws IOException, ParseException {
		var line = readLine();
		if (line != null) {
			var values = line.split(delimiter);
			if (values.length != cols) throw new ParseException(String.format("Unexpected column count at line %d", linesRead));
			return mapper.map(values);
		}
		return null;
	}

	/**
	 * Reads all remaining elements and adds them to the provided collection.
	 *
	 * @param collection the collection to fill
	 * @param <C> type of the collection to read into
	 * @return the same collection for convenience
	 *
	 * @throws NullPointerException if the provided collection is null
	 * @throws IOException if an I/O error occurs
	 * @throws ParseException if an error occurs in the underlying {@link ObjectMapper<T>}
	 *
	 * @apiNote If an error occurs before reaching the end, the provided collection may
	 * 			contain some of the successfully parsed elements.
	 */
	public <C extends Collection<T>> C readRemainingInto(C collection) throws IOException, ParseException {
		Objects.requireNonNull(collection, "Cannot read into collection null");
		var read = readNext();
		while (read != null) {
			collection.add(read);
			read = readNext();
		}
		return collection;
	}

	/**
	 * This is a utility method that reads all rows from a CSV file at the given path and maps them into objects of type {@code T}
	 * using the provided {@link ObjectMapper}.
	 *
	 * <p>The file is expected to be a regular file. This method ensures the file exists and is not a directory.
	 *
	 * @param path       path to the CSV file
	 * @param mapper     the {@link ObjectMapper} used to map rows into objects
	 * @param delimiter  CSV delimiter
	 * @param cols       expected number of columns per row
	 * @param skipHeader whether to skip the first row
	 * @param <T>        the type of objects to read
	 * @return list of objects read from the CSV file
	 *
	 * @throws NullPointerException   if {@code path} or {@code mapper} is null
	 * @throws NoSuchFileException    if the file does not exist or is not a regular file
	 * @throws IOException            if an I/O error occurs while reading the file
	 * @throws ParseException         if a parsing error occurs while mapping rows
	 */
	public static <T> List<T> readAllFrom(Path path, ObjectMapper<T> mapper, String delimiter, int cols, boolean skipHeader)
			throws IOException, ParseException {
		Objects.requireNonNull(path, "Cannot read null path");
		Objects.requireNonNull(mapper, "ObjectMapper cannot be null");
		requireRegularFile(path);
		try (var parser = new CSVParser<>(path, mapper, delimiter, cols, skipHeader)) {
			return parser.readRemainingInto(new ArrayList<>());
		}
	}

	private static void requireRegularFile(Path path) throws NoSuchFileException {
		if (!Files.isRegularFile(path)) {
			throw new NoSuchFileException(path.toAbsolutePath().toString());
		}
	}

	private String readLine() throws IOException {
		if (skipNext) {
			skipNext = false;
			reader.readLine();
			linesRead++;
		}

		var line = reader.readLine();
		if (line != null) linesRead++;
		return line;
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

	@FunctionalInterface
	public interface ObjectMapper<T> {
		T map(String[] values) throws ParseException;
	}

	public static class ParseException extends Exception {
		public ParseException(Exception e) {
			super(e);
		}

		public ParseException(String message) {
			super(message);
		}
	}
}
