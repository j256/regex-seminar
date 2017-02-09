package com.j256.regexseminar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Demonstration of greedy regex versus lazy.
 * 
 * @author graywatson
 */
public class GreedyVersusLazyTest {

	@Test
	public void testGreedy() {

		/*
		 * Any number of any character, then 1 or more digits. On the surface this looks fine but the problem is that .*
		 * is a "greedy" pattern and also matches digits. Because of the + after \\d there must be one but one, but the
		 * group will never match more than 1.
		 */
		Pattern pattern = Pattern.compile(".*(\\d+)");

		Matcher matcher = pattern.matcher("file12");
		// it matches
		assertTrue(matcher.matches());
		// but because the .* is "greedy" then it will absorb the file and the 1 and leave the 2 do the \\d+ only.
		assertEquals("2", matcher.group(1));

		/*
		 * We can work around this by putting a non-digit character right before it switches to digits.
		 */
		pattern = Pattern.compile(".*[^\\d](\\d+)");

		matcher = pattern.matcher("file12");
		// it matches
		assertTrue(matcher.matches());
		// but because the .* is "greedy" then it will absorb the file and the 1 and leave the 2 do the \\d+ only.
		assertEquals("12", matcher.group(1));

		/*
		 * A possibly better way to handle this is to add a ? after the .*, it turns it into a lazy pattern. This means
		 * that the .* will give characters to later patterns.
		 */
		pattern = Pattern.compile(".*?(\\d+)");

		matcher = pattern.matcher("file12");
		// it matches
		assertTrue(matcher.matches());
		// but because the .* is "greedy" then it will absorb the file and the 1 and leave the 2 do the \\d+ only.
		assertEquals("12", matcher.group(1));
	}
}
