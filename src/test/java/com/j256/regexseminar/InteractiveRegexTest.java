package com.j256.regexseminar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Prompts for a regular expression pattern and then lines to match on.
 * 
 * @author graywatson
 */
public class InteractiveRegexTest {

	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	@Test
	public void testStuff() throws IOException {
		Pattern pattern = Pattern.compile(".*");
		testPattern(pattern);
		pattern = Pattern.compile("(.).*");
		testPattern(pattern);
	}

	private void testPattern(Pattern pattern) throws IOException {

		System.out.println("Pattern = " + pattern);
		while (true) {
			System.out.print("Enter string to match (\"quit\" to finish): ");
			String line = reader.readLine();
			if ("quit".equals(line)) {
				System.out.println("----------------------------------");
				return;
			}

			Matcher matcher = pattern.matcher(line);
			if (!matcher.matches()) {
				System.out.println("no match");
				continue;
			}
			System.out.print("matches");
			if (matcher.groupCount() > 0) {
				System.out.print(": ");
				for (int i = 0; i <= matcher.groupCount(); i++) {
					if (i > 0) {
						System.out.print(", ");
					}
					System.out.print('\'' + matcher.group(i) + '\'');
				}
			}
			System.out.println();
		}
	}
}
