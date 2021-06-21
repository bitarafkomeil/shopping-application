package com.assignment.DTO.entity.create;

import com.assignment.DTO.entity.base.BaseUserDTO;
import com.assignment.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link User} entity.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreateUserDTO extends BaseUserDTO implements Serializable {

    @NotNull
    private String password;
}
