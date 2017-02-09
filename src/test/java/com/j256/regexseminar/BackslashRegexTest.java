package com.j256.regexseminar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Test using the backslash character: \
 * 
 * @author graywatson
 */
public class BackslashRegexTest {

	@Test
	public void testFileExtensionWithoutBackslash() {

		// some characters and then a extension, right? (wrong)
		Pattern pattern = Pattern.compile(".+.doc");

		Matcher matcher = pattern.matcher("file1.doc");
		// yay!!
		assertTrue(matcher.matches());

		matcher = pattern.matcher("sindoc");
		// boo!!
		assertTrue(matcher.matches());
	}

	@Test
	public void testFileExtensionWithProperBackslash() {

		// some characters and then a extension, right? (RIGHT), the backslash turns . into just a normal character
		Pattern pattern = Pattern.compile(".+\\.doc");  // the regex pattern matcher sees:  .+\.doc

		Matcher matcher = pattern.matcher("file1.doc");
		// yay!!
		assertTrue(matcher.matches());

		matcher = pattern.matcher("sindoc");
		// phew!
		assertFalse(matcher.matches());
	}

	@Test
	public void testBackslashOtherChars() {

		// this matches one of more "word" chars, then a period, then one or more digits
		Pattern pattern = Pattern.compile("\\w+\\.\\d+");

		Matcher matcher = pattern.matcher("file.1");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("f.010");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("lower012UPPER.010");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("0.a");
		assertFalse(matcher.matches());
	}

	@Test
	public void testWhitespace() {

		// matches optional whitespace, then "hello", then 1 or more spaces, then "there", lastly optional whitespace
		Pattern pattern = Pattern.compile("\\s*hello\\s+there\\s*");

		Matcher matcher = pattern.matcher("hello there");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("    hello      there    ");
		assertTrue(matcher.matches());

		matcher = pattern.matcher("\t\t\t\t   \t\t hello \t\t there");
		assertTrue(matcher.matches());

		// because of the \\s+ in the middle we need to have at least 1 whitespace char between hello and there
		matcher = pattern.matcher("hellothere");
		assertFalse(matcher.matches());
	}
}
