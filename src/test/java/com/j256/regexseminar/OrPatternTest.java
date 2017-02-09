package com.j256.regexseminar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Use of | OR character in regular expressions.
 * 
 * @author graywatson
 */
public class OrPatternTest {

	@Test
	public void testThisOrThat() {

		// cats | dogs
		Pattern pattern = Pattern.compile("cats|dogs");

		Matcher matcher = pattern.matcher("cats");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("dogs");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("catsdogs");
		assertFalse(matcher.matches());
	}

	@Test
	public void testOrInGrouping() {

		// cats | dogs
		Pattern pattern = Pattern.compile("Something to (eat|drink)");

		Matcher matcher = pattern.matcher("Something to eat");
		assertTrue(matcher.matches());
		assertEquals("eat", matcher.group(1));

		matcher = pattern.matcher("Something to drink");
		assertTrue(matcher.matches());
		assertEquals("drink", matcher.group(1));
	}
}
