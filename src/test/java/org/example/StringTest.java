package org.example;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTest {

	@Test
	void upperCaseResultsInAnUpperCaseString() {
		String s = "Hello, World!";

		String upp = s.toUpperCase(Locale.ROOT);

		assertEquals("HELLO, WORLD!", upp);
	}
}
