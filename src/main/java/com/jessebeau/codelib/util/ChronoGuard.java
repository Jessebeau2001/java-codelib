package com.jessebeau.codelib.util;

import java.time.LocalDateTime;

/**
 * This a static utility class that aims to help time-based with input validation.
 *
 * @author Jessebeau2001
 */
public final class ChronoGuard {
	private ChronoGuard() { }

	/**
	 * Ensures that the given {@code LocalDateTime} is in the future using {@code LocalDateTime.now()} interally.
	 *
	 * @param datetime the value to check
	 * @throws IllegalArgumentException if {@code datetime} is in the past
	 * @since 0.1
	 */
	public static void requireInFuture(LocalDateTime datetime) {
		// This kinda sucks, the caller should be able to define when 'now' actually is
		if (LocalDateTime.now().isAfter(datetime)) {
			throw new IllegalArgumentException(
					String.format("Time (%s) must not be in the past", datetime)
			);
		}
	}

	/**
	 * Ensures that the start time is not after the end time.
	 *
	 * @param start the start time
	 * @param end   the end time
	 * @throws IllegalArgumentException if {@code start} is after {@code end}
	 * @since 0.1
	 */
	public static void requireChronologicalOrder(LocalDateTime start, LocalDateTime end) {
		if (start.isAfter(end)) {
			throw new IllegalArgumentException(
					String.format("Start time (%s) cannot be after end time (%s)", start, end)
			);
		}
	}
}
