package com.j256.regexseminar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Demonstration of using parens as groupings.
 * 
 * @author graywatson
 */
public class GroupingTest {

	@Test
	public void testGroupingWithPlusStarQuestion() {

		// match the word cat and then an optional "and dogs"
		Pattern pattern = Pattern.compile("cats( and dogs)?");

		Matcher matcher = pattern.matcher("cats");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("cats and dogs");
		assertTrue(matcher.matches());

		// no match because the ? means 0 or 1 of " and dogs" not 2 of them
		matcher = pattern.matcher("cats and dogs and dogs");
		assertFalse(matcher.matches());

		// match the word cat and then one or more "and dogs"
		pattern = Pattern.compile("cats( and dogs)+");

		matcher = pattern.matcher("cats and dogs");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("cats and dogs and dogs and dogs");
		assertTrue(matcher.matches());

		// this doesn't match because there has to be 1 or more " and dogs" at the end
		matcher = pattern.matcher("cats");
		assertFalse(matcher.matches());
	}

	@Test
	public void testExtractingGroups() {

		// common pattern: field=value
		Pattern pattern = Pattern.compile("(\\w+)=(.*)");

		// with the pattern we can match a string
		Matcher matcher = pattern.matcher("field=value");
		// NOTE: we need to run the matches method before we can look at the groups below
		assertTrue(matcher.matches());

		// now we can use the matcher.group(int)
		// group 0 is the entire matched string
		assertEquals("field=value", matcher.group(0));
		assertEquals("field", matcher.group(1));
		assertEquals("value", matcher.group(2));

		matcher = pattern.matcher("foo=12");
		assertTrue(matcher.matches());

		assertEquals("foo=12", matcher.group(0));
		assertEquals("foo", matcher.group(1));
		assertEquals("12", matcher.group(2));

		/*
		 * same common pattern now with optional whitespace, some word chars, optional whitespace, equals, optional
		 * whitespace, other stuff
		 */
		pattern = Pattern.compile("\\s*(\\w+)\\s*=\\s*(.*)");

		matcher = pattern.matcher("field=value");
		assertTrue(matcher.matches());
		assertEquals("field", matcher.group(1));
		assertEquals("value", matcher.group(2));

		matcher = pattern.matcher("field = value");
		assertTrue(matcher.matches());
		assertEquals("field", matcher.group(1));
		assertEquals("value", matcher.group(2));

		matcher = pattern.matcher("   foo = bar");
		assertTrue(matcher.matches());
		assertEquals("foo", matcher.group(1));
		assertEquals("bar", matcher.group(2));

		matcher = pattern.matcher("   regex    =    fun");
		assertTrue(matcher.matches());
		assertEquals("regex", matcher.group(1));
		assertEquals("fun", matcher.group(2));
	}
}
