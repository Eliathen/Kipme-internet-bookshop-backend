package com.bookshop.core.security.exceptionhandler;

import com.bookshop.core.exceptions.ErrorInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        logger.error(e.getMessage());
        httpServletResponse.sendError(
                HttpServletResponse.SC_FORBIDDEN,
                objectMapper.writeValueAsString(
                        new ErrorInfo(
                                LocalDateTime.now(),
                                Collections.singletonList(e.getMessage())
                        )
                )
        );
    }
}
