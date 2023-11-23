package com.bookshop.features.user.validators;

import com.bookshop.features.user.exception.InvalidEmailAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class EmailValidatorTest {


    @ParameterizedTest
    @CsvFileSource(resources = "/valid-email-addresses.csv")
    void shouldPassValidationWhenGivenValidEmail(String email) {
        Assertions.assertDoesNotThrow(() -> EmailValidator.validate(email));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalid-email-addresses.csv")
    void shouldThrowExceptionWhenGivenInvalidEmail(String email) {

        Assertions.assertThrows(InvalidEmailAddress.class, () -> EmailValidator.validate(email));
    }


}