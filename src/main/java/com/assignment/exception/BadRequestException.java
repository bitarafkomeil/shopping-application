package com.assignment.exception;

import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

@Getter
public class BadRequestException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public BadRequestException(URI type, String defaultMessage) {
        super(type, defaultMessage, Status.BAD_REQUEST);
    }
}