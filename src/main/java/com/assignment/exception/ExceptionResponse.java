package com.assignment.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ExceptionResponse {

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private String message;

    @NotNull
    private Integer Status;

    @NotNull
    private URI path;
}