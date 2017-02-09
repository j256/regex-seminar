package com.j256.regexseminar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Basic regular expressions.
 * 
 * @author graywatson
 */
public class BasicRegexTest {

	@Test
	public void testQuestion() {

		// match the start dog with 0 or 1 "s" at the end, so this should match "dog" or "dogs"
		Pattern pattern = Pattern.compile("dogs?");

		// with the pattern we can match a string
		Matcher matcher = pattern.matcher("dog");
		assertTrue(matcher.matches());

		// same pattern can match many strings
		matcher = pattern.matcher("dogs");
		assertTrue(matcher.matches());

		// but one 0 or 1 s, not 2
		matcher = pattern.matcher("dogss");
		assertFalse(matcher.matches());
	}

	@Test
	public void testDotStarPattern() {

		// we compile our pattern, this can be expensive so should do as a final field if possible
		// . is any character, * means 0 or more of the previous, so this means 0 or more of any character
		Pattern pattern = Pattern.compile(".*");

		// with the pattern we can match a string
		Matcher matcher = pattern.matcher("hello");
		assertTrue(matcher.matches());

		// same pattern can match many strings
		matcher = pattern.matcher("another");
		assertTrue(matcher.matches());

		// matches the empty string because . means any character and * means 0 or more instances of it
		matcher = pattern.matcher("");
		assertTrue(matcher.matches());
	}

	@Test
	public void testLetterStarPattern() {

		// a matches the letter 'a', * means 0 or more of the previous, so this means 0 or more 'a' followed by "nt"
		Pattern pattern = Pattern.compile("a*nt");

		// with the pattern we can match a string
		Matcher matcher = pattern.matcher("ant");
		assertTrue(matcher.matches());

		// you can have any number of aaaaaaa's
		matcher = pattern.matcher("aaaant");
		assertTrue(matcher.matches());

		// no a's is also good
		matcher = pattern.matcher("nt");
		assertTrue(matcher.matches());
	}

	@Test
	public void testDotPlusPattern() {

		// . is any character, + means 1 or more of the previous, so this means 1 or more of any character
		Pattern pattern = Pattern.compile(".+");

		// with the pattern we can match a string
		Matcher matcher = pattern.matcher("hello");
		assertTrue(matcher.matches());

		// HOWEVER, since it is a + then this doesn't match the empty string
		matcher = pattern.matcher("");
		assertFalse(matcher.matches());
	}

	@Test
	public void testLetterPlusPattern() {

		// 1 or more z's followed by "ebra"
		Pattern pattern = Pattern.compile("z+ebra");

		// 1 z here
		Matcher matcher = pattern.matcher("zebra");
		assertTrue(matcher.matches());

		// many z's here
		matcher = pattern.matcher("zzzzzzzebra");
		assertTrue(matcher.matches());

		// but this DOESN'T match because + means 1 or more and there are 0 z's here
		matcher = pattern.matcher("ebra");
		assertFalse(matcher.matches());
	}
}
