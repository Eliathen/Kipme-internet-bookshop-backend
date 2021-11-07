package com.bookshop.core.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String RESPONSE_WITH_UNAUTH_ERROR = "Responding with unauthorized error. Message - {}";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String EXCEPTION_TRACK_DELIMITER = "\n";
    public static final String HOME_URL = "https://localhost:443";

    public static String[] getUnprotectedEndpoints() {
        return new String[]{
                "/swagger-ui/**",
                "/h2-console/**",
                "/users/login",
                "/users/signup",
        };
    }

}
