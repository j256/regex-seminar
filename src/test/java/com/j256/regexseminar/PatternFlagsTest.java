package org.aneteng.samples.regex;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Some examples of using flags, curly braces {}, and | or symbol.
 * 
 * @author graywatson
 */
public class PatternFlagsTest {

	@Test
	public void testInsensitiveCaseFlag() {

		// cats all lowercase
		Pattern pattern = Pattern.compile("cats");

		Matcher matcher = pattern.matcher("cats");
		assertTrue(matcher.matches());
		matcher = pattern.matcher("Cats");
		assertFalse(matcher.matches());

		// turn on the case-insensitive flag
		pattern = Pattern.compile("(?i)cats");
		matcher = pattern.matcher("cats");
		assertTrue(matcher.matches());
		matcher = pattern.matcher("Cats");
		assertTrue(matcher.matches());
		matcher = pattern.matcher("caTS");
		assertTrue(matcher.matches());
	}

	/**
	 * See See {@link Pattern#DOTALL}.
	 */
	@Test
	public void testSingleLineFlag() {

		// hello whitespace world
		Pattern pattern = Pattern.compile("hello.world");

		// a space is fine
		Matcher matcher = pattern.matcher("hello world");
		assertTrue(matcher.matches());
		/*
		 * This doesn't match below because . by default . DOESN'T MATCH NEWLINES. This trips me up a lot!
		 */
		matcher = pattern.matcher("hello\n" //
				+ "world");
		assertFalse(matcher.matches());

		// now try it with the single line switch (documented as DOT-ALL), now . also matches newline chars
		pattern = Pattern.compile("(?s)hello.world");
		matcher = pattern.matcher("hello world");
		assertTrue(matcher.matches());
		matcher = pattern.matcher("hello\n" //
				+ "world");
		assertTrue(matcher.matches());
	}
}
