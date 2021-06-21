package com.assignment.DTO.entity.read;

import com.assignment.DTO.entity.IdentifiableDTO;
import com.assignment.DTO.entity.base.BaseUserDTO;
import com.assignment.model.User;
import com.assignment.model.enumeration.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link User} entity.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseUserDTO implements IdentifiableDTO,Serializable {

    @Size(min = 2, max = 50)
    private Long id;

    @NotNull
    private UserRole role;

    @NotNull
    private Boolean block;
}