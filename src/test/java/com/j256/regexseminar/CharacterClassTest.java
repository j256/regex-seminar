package org.aneteng.samples.regex;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Use of [] character class regex patterns.
 * 
 * @author graywatson
 */
public class CharacterClassTest {

	@Test
	public void testCharacterClassSquareBrackets() {

		// the word hello but the 2nd l can be 'l' or 'L' or '1'
		Pattern pattern = Pattern.compile("hel[lL1]o");

		Matcher matcher = pattern.matcher("hello");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("helLo");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("hel1o");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("helxo");
		assertFalse(matcher.matches());
	}

	@Test
	public void testCharacterClassRange() {

		// the word hell followed by a character from 0 thru 8 (not 9)
		Pattern pattern = Pattern.compile("hell[0-8]");

		Matcher matcher = pattern.matcher("hell0");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("hell1");
		assertTrue(matcher.matches());

		// no 9 so no match
		matcher = pattern.matcher("hell9");
		assertFalse(matcher.matches());
	}

	@Test
	public void testCombinationOfCharacterClassAndPlus() {

		// character from 0 thru 8 one of more times
		Pattern pattern = Pattern.compile("[0-8]+");

		Matcher matcher = pattern.matcher("0");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("01");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("000123");
		assertTrue(matcher.matches());

		// no 9 so no match
		matcher = pattern.matcher("0001239");
		assertFalse(matcher.matches());

		// and there needs to be at least 1 of them because of the +
		matcher = pattern.matcher("");
		assertFalse(matcher.matches());
	}
}
