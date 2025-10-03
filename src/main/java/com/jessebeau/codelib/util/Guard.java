package com.jessebeau.codelib.util;

/**
 * This a static utility class that aims to help general with input validation.
 *
 * @author Jessebeau2001
 */
public final class Guard {
	private Guard() { }

	/**
	 * Ensures that the specified integer is less than the given maximum value using a non-strict check:
	 * <blockquote><pre>
	 * if (value > max) throw
	 * </pre></blockquote>
	 *
	 * @param value the integer to limit
	 * @param max the maximum value of the integer
	 * @throws IllegalArgumentException if the value exceeds the given maximum
	 * @since 0.1
	 */
	public static void requireAtMost(int value, int max) {
		if (value > max) {
			throw new IllegalArgumentException(
					String.format("Value must be less than %d - given %d", max, value)
			);
		}
	}

	/**
	 * Ensures that the specified integer is greater than the given minimum value using a non-strict check:
	 * <blockquote><pre>
	 * if (value < min) throw
	 * </pre></blockquote>
	 *
	 * @param value the integer to floor
	 * @param min the minimum value of the integer
	 * @throws IllegalArgumentException if the value is less than the given minimum
	 * @since 0.1
	 */
	public static void requireAtLeast(int value, int min) {
		if (value < min) {
			throw new IllegalArgumentException(
					String.format("Value must be greater than %d - was %d", min, value)
			);
		}
	}

	/**
	 * Ensures that the specified integer is within the given range using strict checking:
	 * <blockquote><pre>
	 * if (value < min || value > max) throw
	 * </pre></blockquote>
	 *
	 * @param value the integer to check
	 * @param min the minimum required value
	 * @param max the maximum required value
	 * @throws IllegalArgumentException if the value is outside the given range
	 * @since 0.1
	 */
	public static void requireInRange(int value, int min, int max) {
		if (value < min || value > max) {
			throw new IllegalArgumentException(
					String.format("Value most be in range (%d, %d) - was: %d", min, max, value)
			);
		}
	}

	/**
	 * Ensures that the given string's length does not exceed the specified maximum.
	 *
	 * @param value   the string to check
	 * @param max     the maximum allowed length
	 * @param message the exception message if the check fails
	 * @throws IllegalArgumentException if {@code length() > max}
	 * @since 0.1
	 */
	public static void requireLengthAtMost(String value, int max, String message) {
		if (value.length() > max) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Ensures that the given value is not a blank string.
	 *
	 * @param value the value to check
	 * @throws IllegalArgumentException if {@code value.trim().isBlank()}
	 * @since 0.1
	 */
	public static void requireNonBlank(String value) {
		if(value.trim().isBlank()) {
			throw new IllegalArgumentException("Value cannot be blank");
		}
	}
}
