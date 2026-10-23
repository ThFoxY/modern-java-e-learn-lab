package org.example;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

// Sources:
// <a href="https://blog.jetbrains.com/idea/2024/03/easy-hacks-how-to-throw-java-exceptions/">Easy Hacks: How to Throw Java Exceptions</a>
// <a href="https://www.itflashcards.com/cheatsheets/regex/">Regex Cheat Sheet</a>
// <a href="https://wordcounter.ai/remove-accents/">Remove Accents from Text</a>
// <a href="https://www.jmdoudoux.fr/java/dej/index.htm">Développons en Java</a>
// <a href="https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html#tag">How to Write Doc Comments for the Javadoc Tool</a>

/**
 * Converts a string into its slug format, useful for URLs or filenames.
 * <p>
 *     A slug is lowercase, contains only ASCII letters, digits and hyphens,
 *     and has no leading, trailing, or consecutive hyphens. Input is normalized
 *     to NFD, accents are stripped, and common ligatures (ß, æ, œ, ø, ł, þ)
 *     are transliterated to their ASCII equivalents.
 * </p>
 *
 * @apiNote The NFD normalization does not decompose every character;
 *          transliteration covers only the most common ligatures. Any
 *          other non-ASCII, non-decomposable character is dropped.
 * @implNote The transliteration is not implemented yet; only a map of
 *           common ligatures exists for now. Must handle:
 *           - The German ß (eszett),
 *           - The Scandinavian Æ, Œ, Ø,
 *           - The Polish Ł (slashed L) and the Icelandic Þ (thorn).
 *           Tests featuring these inputs must also be added.
 * @author david
 * @version 1.1
 */
public final class Slugifier {
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
    private static final Pattern NON_ALPHANUMERIC = Pattern.compile("[^a-z0-9]+");
    private static final Pattern SEP_ACCENTS = Pattern.compile("\\p{M}");
    private static final Pattern SINGLE_HYPHEN = Pattern.compile("^(-+)|(-+)$");

    private Slugifier() {
        throw new AssertionError("Utility class must not be instantiated");
    }

    /**
     * Converts the given string to a slug.
     *
     * @param s the string to convert, may be empty but not {@code null}
     * @return the slug form of the input, never {@code null}; empty if the
     *         input contains no slugify-able character
     * @throws IllegalArgumentException if {@code s} is {@code null}
     */
    public static String slugify(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string must not be null");
        }

        // 1. Converts to lowercase
        String slug = s.toLowerCase(Locale.ROOT);

        // 2. Formats to NFD (Normalization Form D) to handle accented strings
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);

        // 3. Removes separated accents
        slug = SEP_ACCENTS.matcher(slug).replaceAll("");

        // 4. Replaces any run of non-[a-z0-9] with a single hyphen
        slug = NON_ALPHANUMERIC.matcher(slug).replaceAll("-");

        // 5. Removes leading and trailing hyphens
        return SINGLE_HYPHEN.matcher(slug).replaceAll("");
    }
}
