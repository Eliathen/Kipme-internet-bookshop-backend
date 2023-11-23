package com.bookshop.features.user.validators;

import com.bookshop.features.user.exception.InvalidEmailAddress;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmailValidatorTest {


    @ParameterizedTest
    @CsvFileSource(resources = "/valid-email-addresses.csv")
    void shouldPassValidationWhenGivenValidEmail(String email) {
        assertThatNoException().isThrownBy(() -> EmailValidator.validate(email));
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/invalid-email-addresses.csv")
    void shouldThrowExceptionWhenGivenInvalidEmail(String email) {
        assertThatThrownBy(() -> EmailValidator.validate(email)).isInstanceOf(InvalidEmailAddress.class);
    }


}