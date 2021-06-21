package com.assignment.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URI;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorConstants {

    public static final String PROBLEM_BASE_URL = "shopping";

    public static final URI NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/not-found");
    public static final URI VALIDATION_TYPE = URI.create(PROBLEM_BASE_URL + "/invalid");
}