package org.example;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Sources:
 * <a href="https://blog.jetbrains.com/idea/2024/03/easy-hacks-how-to-throw-java-exceptions/">Easy Hacks: How to Throw Java Exceptions</a>
 * <a href="https://www.itflashcards.com/cheatsheets/regex/">Regex Cheat Sheet</a>
 */

public class Slugifier {
    private Slugifier() {
        /* This utility class should not be instantiated */
    }

    /**
     * Any character that is not a lowercase letter, a digit or a hyphen
     *  [...] (Character Class): matches any single character contained inside the brackets
     *  ^: Negates the set, meaning "anything except the characters listed below"
     *  a-z: Any lowercase letter from a to z
     *  0-9: Any digit from 0 to 9
     *  -: A literal hyphen (placed at the end of the character class so it is not mistaken for a range)
     * Matches:
     *  Uppercase letters (A, Z)
     *  Spaces ( )
     *  Symbols (@, #, !)
     *  Accented or non-English characters (é, ö, à, î)
     */
    private static final Pattern NON_ALPHANUMERIC = Pattern.compile("[^a-z0-9-]");
    /**
     * Matches any single whitespace character, including:
     *  Spaces ( )
     *  Tabs (\t)
     *  Newlines (\n)
     *  Carriage returns (\r)
     * + (Quantifier): means "match one or more times" of the preceding token
     * The first \ escapes the \s character
     */
    private static final Pattern WHITESPACE = Pattern.compile("\\s+");
    private static final Pattern DUP_HYPHEN = Pattern.compile("-+");

    public static String stringToSlug(String s) throws IllegalArgumentException {
        if (s == null) {
            throw new IllegalArgumentException("Threw an error here!");
        }

        // 1. Converts to lowercase
        String slug = s.toLowerCase(Locale.ROOT);

        // 2. Replaces spaces with hyphens
        slug = WHITESPACE.matcher(slug).replaceAll("-");

        // 3. Formats to NFD (Normalization Form D) to handle accented strings
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);

        // 4. Removes separated accents
        slug = slug.replaceAll("\\p{M}", "");

        // 5. Removes any character that is not a lowercase letter, a digit or a hyphen
        slug = NON_ALPHANUMERIC.matcher(slug).replaceAll("");

        // 6. Removes consecutive hyphens
        slug = DUP_HYPHEN.matcher(slug).replaceAll("-");

        // 7. Removes starting and ending hyphens
        if (slug.startsWith("-")) {
            slug = slug.substring(1);
        }
        if (slug.endsWith("-")) {
            slug = slug.substring(0, slug.length() - 1);
        }

        return slug;
    }
}
