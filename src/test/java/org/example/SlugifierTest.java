package org.example;

import static org.example.Slugifier.stringToSlug;
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
        String actualNonStrippedString = stringToSlug("Hello, World!");
        String expectedSlug = "hello-world";

        assertEquals(expectedSlug, actualNonStrippedString, "There is an error message here!");
    }

    @Test
    @DisplayName("Returns the stripped slug of a non-stripped string")
    void givenANonStrippedString_whenStringToSlug_thenReturnStrippedSlug() {
        String actualSimpleString = stringToSlug("  Hello, World!  ");
        String expectedStrippedSlug = "  hello-world  ".strip(); // "hello-world" without blank spaces

        assertEquals(expectedStrippedSlug, actualSimpleString, "There is an error message here!");
    }

    @Test
    @DisplayName("Returns an empty slug of a empty string")
    void givenAnEmptyString_whenStringToSlug_thenReturnAnEmptySlug() {
        String actualEmptyString = stringToSlug("");
        String expectedEmptySlug = "";

        assertEquals(expectedEmptySlug, actualEmptyString, "There is an error message here!");
    }

    @Test
    @DisplayName("Throws an IllegalArgumentException when the string is null")
    void givenANullString_whenStringToSlug_thenThrowAnIllegalArgumentException() {
        IllegalArgumentException expectedException =
                assertThrows(IllegalArgumentException.class, () -> {
                    stringToSlug(null);
                });

        String expectedMessage = "Threw an error here!";
        String actualMessage = expectedException.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Returns a normalized slug of an accented string")
    void givenAnAccentedString_whenStringToSlug_thenReturnANormalizedSlug() {
        String actualAccentedString = stringToSlug("Café");
        String expectedNormalizedSlug = "cafe";

        assertEquals(expectedNormalizedSlug, actualAccentedString, "There is an error message here!");
    }
}
