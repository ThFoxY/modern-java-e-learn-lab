package org.example;

import static org.example.Slugifier.slugify;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

// Sources:
// <a href="https://www.baeldung.com/java-unit-testing-best-practices">Best Practices for Unit Testing in Java</a>
// <a href="https://docs.junit.org/6.1.3/writing-tests/intro.html">JUnit User Guide 6.1.3</a>
// <a href="https://www.baeldung.com/junit-assert-exception">Assert an Exception Is Thrown in JUnit 4 and 5</a>
// <a href="https://medium.com/@andrei.oleynik/junit-6-from-scratch-6d4be3f04974#4ccf">JUnit 6 from Scratch: What a Test Framework Actually Does</a>

/**
 * Unit tests for {@link Slugifier}
 * <p>
 *     Tests follow the Given-When-Then (BDD) format. Standard cases
 *     are grouped in a parameterized test; edge case (null input) have
 *     a dedicated test.
 * </p>
 *
 * @see Slugifier
 */
class SlugifierTest {

    // Given, When and Then (Behavior-Driven Development) format
    @ParameterizedTest(name = "{index} ==> \"{0}\" -> \"{1}\"")
    @CsvSource({
            // input                // expected
            "Hello, World!,         hello-world",
            "Hello,World!,          hello-world",   // no spacing
            "'  Hello, World!  ',   hello-world",   // multiple spaces
            "hello---world,         hello-world",   // multiple hyphens
            "-a-,                   a",             // hyphens at borders
            "!!!,                   ''",            // empty
            "abc-123,               abc-123",       // digits
            "crême brûlée,          creme-brulee"   // multiple accents
    })
    @DisplayName("Returns the expected slug for various inputs")
    void givenAnInputString_whenSlugified_thenReturnExpectedSlug(String input, String expected) {
        assertEquals(expected, slugify(input),
                () -> "slugify(\"" + input + "\") should return \"" + expected + "\""); // Lambda function, only evaluated when a fail occurs
    }

    @Test
    @DisplayName("Throws an IllegalArgumentException when the string is null")
    void givenANullString_whenSlugified_thenThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> slugify(null));

        assertEquals("Input string must not be null", exception.getMessage());
    }
}
