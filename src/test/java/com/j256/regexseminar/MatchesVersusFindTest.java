package com.j256.regexseminar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Showing the difference between matcher.matches() versus matcher.find().
 * 
 * @author graywatson
 */
public class MatchesVersusFindTest {

	@Test
	public void testMatchesVersusFind() {

		// this is the pattern of 'l' char and then another character
		Pattern pattern = Pattern.compile("l.");
		Matcher matcher = pattern.matcher("hello folks");
		// the pattern doesn't match the full string
		assertFalse(matcher.matches());

		// but it works as a find which looks for a substring match (NOTE: this is the default in perl's regex)
		assertTrue(matcher.find());
		// group 0 is the actually matched substring
		assertEquals("ll", matcher.group(0));
		// the start/end methods tells us _where_ in the string the pattern matched
		assertEquals(2, matcher.start());
		assertEquals(4, matcher.end());

		// we can do another find which looks forward from the previous found location
		assertTrue(matcher.find());
		// and find the next instance of "l." this time "lk"
		assertEquals("lk", matcher.group(0));
		assertEquals(8, matcher.start());
		assertEquals(10, matcher.end());

		// no more instances of the pattern so the 3rd find returns false
		assertFalse(matcher.find());
	}
}
