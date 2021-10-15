package com.bookshop.core.exceptions;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ErrorInfo {
    LocalDateTime timestamp;
    String message;
}
