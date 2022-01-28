package com.bookshop.features.user.validators;

import com.bookshop.features.user.exception.InvalidEmailAddress;
import com.bookshop.features.user.utils.Constants;

import java.util.regex.Pattern;

public class EmailValidator {

    public static void validate(String email) {
        if (!Pattern.compile(Constants.EMAIL_REGEX_EXPRESSION).matcher(email).matches())
            throw new InvalidEmailAddress();
    }

}
