package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {
    @ParameterizedTest
    @CsvSource({"Isaac,,Newton,Isaac Newton", "Charles,Robert,Darwin,Charles Robert Darwin"})
    void fullName_ShouldGenerateTheExpectedFullName(ArgumentsAccessor argumentsAccessor) {
        String firstName = argumentsAccessor.getString(0);
        String middleName = (String) argumentsAccessor.get(1);
        String lastName = argumentsAccessor.get(2, String.class);
        String expectedFullName = argumentsAccessor.getString(3);

        Person person = new Person(firstName, middleName, lastName);
        assertEquals(expectedFullName, person.fullName());
    }

    private static Stream<Arguments> forPersonComputeFullName(){
        return Stream.of(Arguments.of(new Person("Mary", "Anne", "Hathaways"), "Mary Anne Hathaways"));
    }

    @ParameterizedTest
    @MethodSource("forPersonComputeFullName")
    void fullName_ShouldGenerateTheExpectedFullName_ObjectParameter(ArgumentsAccessor argumentsAccessor) {
        Person person = argumentsAccessor.get(0, Person.class);
        String expectedFullName =  argumentsAccessor.getString(1);
        assertEquals(expectedFullName, person.fullName());
    }

    @ParameterizedTest
    @CsvSource({"Isaac Newton,Isaac,,Newton", "Charles Robert Darwin,Charles,Robert,Darwin"})
    void fullName_ShouldGenerateTheExpectedFullName(
            String expectedFullName,
            @AggregateWith(PersonAggregator.class) Person person) {

        assertEquals(expectedFullName, person.fullName());
    }

    private static Stream<Arguments> getFullNameWithPersonAggregator() {
        return Stream.of(
                Arguments.of("Mary Anne Hathaway", "Mary", "Anne", "Hathaway", "Mary", "Anne", "Hathaway")
        );
    }

    @ParameterizedTest
    @MethodSource("getFullNameWithPersonAggregator")
    public void fullName_ShouldGenerateTheExpectedFullName_MethodSource(
            String expectedFullName,
            @AggregateWith(PersonAggregator.class) Person person) {
        assertEquals(expectedFullName, person.fullName());
    }


}
