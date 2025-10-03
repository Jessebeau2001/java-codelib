package com.jessebeau.codelib.util;

import java.util.Random;

/**
 * A static utility class providing additional random generation methods
 * beyond those available in {@link Random}.
 * <p>
 * Includes convenience methods for generating random strings and
 * other common random values.
 *
 * @author Jessebeau2001
 */
public final class MoreRandom {
	private MoreRandom() { }

	/**
	 * Generates a random string of the given length using characters from the specified template.
	 *
	 * @param length   the desired length of the generated string
	 * @param template the string of characters to sample from
	 * @return a random string composed of characters from the template
	 * @since 0.1
	 */
	public static String randomUppercaseString(int length, String template) {
		var random = new Random();
		var sb = new StringBuilder();
		while (sb.length() < length) {
			int index = (int) (random.nextFloat() * template.length());
			sb.append(template.charAt(index));
		}
		return sb.toString();
	}

	/**
	 * Generates a random uppercase string of the given length (A–Z).
	 *
	 * @param length the desired length of the generated string
	 * @return a random uppercase string
	 * @since 0.1
	 */
	public static String randomUppercaseString(int length) {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return randomUppercaseString(length, chars);
	}
}
