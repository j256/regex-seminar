package com.j256.regexseminar;

import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Performance examples and warnings.
 * 
 * @author graywatson
 */
public class PerformanceTest {

	/**
	 * This does a crap-ton of iterations and only shows a small amount of improvement so this is here to keep in mind.
	 * Typically, if your pattern is a constant, you should compile it up at as a constant static field.
	 */
	@Test
	public void testCompileOnFly() {

		final String testRegex = "cat|dog";

		int numTimes = 10000000;

		// does a regex compile every time through the loop
		long start = System.currentTimeMillis();
		for (int i = 0; i < numTimes; i++) {
			String str = "cat";
			str.matches(testRegex);
		}
		System.out.println("With compilation, took " + (System.currentTimeMillis() - start));

		// compiles the pattern up-front
		final Pattern testPattern = Pattern.compile(testRegex);
		start = System.currentTimeMillis();
		for (int i = 0; i < numTimes; i++) {
			String str = "cat";
			testPattern.matcher(str).matches();
		}
		System.out.println("WITHOUT compilation, took " + (System.currentTimeMillis() - start));
	}

	/**
	 * This shows the expense of the string.split() method but it's not as much a win as I thought. Chances are this is
	 * due to the fact that we have very high object bandwidth here as we are generating tens of millions of objects.
	 */
	@Test
	public void testStringSplitVersusStringUtil() {

		final String line = "a,b,c,d,e,f,g,h,i";

		int numTimes = 10000000;

		// compiles regex pattern every time through loop
		long start = System.currentTimeMillis();
		for (int i = 0; i < numTimes; i++) {
			line.split("[,=]");
		}
		System.out.println("String.split took " + (System.currentTimeMillis() - start));

		// does not use a regex
		start = System.currentTimeMillis();
		for (int i = 0; i < numTimes; i++) {
			org.apache.commons.lang3.StringUtils.split(line, ",=");
		}
		System.out.println("StringUtils.split took " + (System.currentTimeMillis() - start));
	}
}
