package org.example;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Person {

    String firstName;
    String middleName;
    String lastName;


    public String fullName() {
        if (middleName == null || middleName.trim().isEmpty()) {
            return String.format("%s %s", firstName, lastName);
        }

        return String.format("%s %s %s", firstName, middleName, lastName);
    }
}
