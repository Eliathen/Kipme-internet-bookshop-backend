package com.bookshop.features.user.validators;

import com.bookshop.features.user.exception.InvalidEmailAddress;
import com.bookshop.features.user.utils.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailValidator {

    public static void validate(String email) {
        if (!Pattern.compile(Constants.EMAIL_REGEX_EXPRESSION).matcher(email).matches())
            throw new InvalidEmailAddress();
    }

}
