package com.bookshop.features.user.validators;

import com.bookshop.features.user.exception.InvalidEmailAddress;
import com.bookshop.features.user.utils.Constants;

public class EmailValidator {

    public static void validate(String email) {
        if (!email.matches(Constants.EMAIL_REGEX_EXPRESSION)) throw new InvalidEmailAddress();
    }

}
