package com.jessebeau.codelib.util;

import java.util.Random;

/**
 * Some extra math utility.
 *
 * @author Jessebeau2001
 */
public final class MathKit {
	private MathKit() { }

	/**
	 * Encodes the given {@code String} to a base36 encoded {@code long}.
	 * Values can only be up to 12 characters long without overflowing the {@code long} type.
	 *
	 * @param value the string value to encode
	 * @return the base36 encoded value
	 * @throws IllegalArgumentException if the encoded value cannot be stored in a long
	 * @apiNote The string can only be 12 characters and will always be encoded in upper-case
	 * @since 0.1
	 */
	public static long encodeBase36(String value) {
		final int max = 12;
		Guard.requireLengthAtMost(value, max, "String cannot exceed max length for base36 encoding (" + max + ")");
		return Long.parseLong(value, 36);
	}

	/**
	 * Decodes a base36 {@code long} back into an upper-case {@code String}
	 *
	 * @param value The value to decode
	 * @return the string decoded from the base36 value
	 * @apiNote The decoded string will always be in upper-case.
	 * @since 0.1
	 */
	public static String decodeBase36(long value) {
		var decoded = Long.toString(value, 36).toUpperCase();
		assert decoded.length() <= 12;
		return decoded;
	}
}
