package com.assignment.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class JwtResponse {

    @NotNull
    private String token;

    @NotNull
    private String role;

    @NotNull
    private String userName;

    @NotNull
    private Long userId;
}