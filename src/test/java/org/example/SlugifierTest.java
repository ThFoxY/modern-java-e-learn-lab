package org.example;

import static org.example.Slugifier.slugify;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Sources:
 * <a href="https://www.baeldung.com/java-unit-testing-best-practices">Best Practices for Unit Testing in Java</a>
 * <a href="https://docs.junit.org/6.1.3/writing-tests/intro.html">JUnit User Guide 6.1.3</a>
 * <a href="https://www.baeldung.com/junit-assert-exception">Assert an Exception Is Thrown in JUnit 4 and 5</a>
 * <a href="https://medium.com/@andrei.oleynik/junit-6-from-scratch-6d4be3f04974#4ccf">JUnit 6 from Scratch: What a Test Framework Actually Does</a>
 */

class SlugifierTest {

    // Given, When and Then (Behavior-Driven Development) format
    @Test
    @DisplayName("Returns the slug of a simple string")
    void givenASimpleString_whenStringToSlug_thenReturnSlug() {
        // Expected vs Actual
        String expectedSlug = slugify("Hello, World!");
        String actualSlug = "hello-world";

        assertEquals(actualSlug, expectedSlug, "There is an error message here!");
    }

    @Test
    @DisplayName("Returns the stripped slug of a non-stripped string")
    void givenANonStrippedString_whenStringToSlug_thenReturnStrippedSlug() {
        String expectedSlug = slugify("  Hello, World!  ");
        String actualSlug = "hello-world";

        assertEquals(actualSlug, expectedSlug, "There is an error message here!");
    }

    @Test
    @DisplayName("Returns an empty slug of a empty string")
    void givenAnEmptyString_whenStringToSlug_thenReturnAnEmptySlug() {
        String expectedSlug = slugify("");
        String actualSlug = "";

        assertEquals(actualSlug, expectedSlug, "There is an error message here!");
    }

    @Test
    @DisplayName("Throws an IllegalArgumentException when the string is null")
    void givenANullString_whenStringToSlug_thenThrowAnIllegalArgumentException() {
        IllegalArgumentException expectedException =
                assertThrows(IllegalArgumentException.class, () -> {
                    slugify(null);
                });

        String expectedMessage = "Threw an error here!";
        String actualMessage = expectedException.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Returns a normalized slug of an accented string")
    void givenAnAccentedString_whenStringToSlug_thenReturnANormalizedSlug() {
        String expectedSlug = slugify("Café");
        String actualSlug = "cafe";

        assertEquals(actualSlug, expectedSlug, "There is an error message here!");
    }

    @Test
    @DisplayName("Returns an hyphen-separated slug of a comma-separated string (no spaces)")
    void givenACommaSeparatedStringWithoutSpaces_whenStringToSlug_thenReturnAnHyphenSeparatedSlug() {
        String expectedSlug = slugify("hello,world!");
        String actualSlug = "hello-world";

        assertEquals(actualSlug, expectedSlug);
    }
}
