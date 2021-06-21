package com.assignment.DTO.entity.base;


import com.assignment.model.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link User} entity.
 */
@Data
public class BaseUserDTO implements Serializable {

    @NotNull
    @Size(min = 2, max = 50)
    private String userName;
}