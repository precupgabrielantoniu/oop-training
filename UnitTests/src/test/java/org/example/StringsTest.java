package org.example;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class StringsTest {

    static Stream<Arguments> streamOf2Arguments = Stream.of(
            Arguments.of(null, true), // null strings should be considered blank
            Arguments.of("", true),
            Arguments.of("  ", true),
            Arguments.of("not blank", false)
    );

    static Stream<Arguments> streamOf1Argument = Stream.of(
            Arguments.of((String) null),
            Arguments.of(""),
            Arguments.of("   ")
    );

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @NullSource
    void isBlank_ShouldReturnTrueForNullInputs(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @EmptySource
    void isBlank_ShouldReturnTrueForEmptyStrings(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void isBlank_ShouldReturnTrueForNullAndEmptyStrings(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void isBlank_ShouldReturnTrueForAllTypesOfBlankStrings(String input) {
        assertTrue(Strings.isBlank(input));
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of("", true),
                Arguments.of("  ", true),
                Arguments.of("not empty string", false)
        );
    }

    @ParameterizedTest(name = "{index} {0} is blank is {1}")
    @MethodSource("provideStringsForIsBlank")
    public void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, Boolean expected) {
        assertEquals(expected, Strings.isBlank(input));
    }

    @ParameterizedTest
    @MethodSource // Method with the same name; no need to be mentioned
    void isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument(String input) {
        assertTrue(Strings.isBlank(input));
    }

    // May return a List, but List cannot contain a null so it's not suited here
    private static Stream<String> isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument() {
        return Stream.of(null, "", "  ");
    }

    @ParameterizedTest
    @MethodSource("org.example.StringParams#blankStrings")
    void isBlank_ShouldReturnTrueForNullOrBlankStringsExternalSource(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @ArgumentsSource(BlankStringsArgumentsProvider.class)
    void isBlank_ShouldReturnTrueForNullOrBlankStringsArgProvider(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @VariableSource("streamOf2Arguments")
    void isBlank_ShouldReturnTrueForNullOrBlankStringsVariableSource(
            String input, boolean expected) {
        assertEquals(expected, Strings.isBlank(input));
    }

    @ParameterizedTest
    @VariableSource("streamOf1Argument")
    void isBlank_ShouldReturnTrueForNullOrBlankStringsVariableSourceAlwaysTrue(
            String input) {
        assertTrue(Strings.isBlank(input));
    }
}
