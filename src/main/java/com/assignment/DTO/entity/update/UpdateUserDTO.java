package com.assignment.DTO.entity.update;

import com.assignment.DTO.entity.IdentifiableDTO;
import com.assignment.DTO.entity.base.BaseUserDTO;
import com.assignment.model.User;
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
public class UpdateUserDTO extends BaseUserDTO implements Serializable, IdentifiableDTO {
    @Size(min = 2, max = 50)
    private Long id;

    @NotNull
    private String password;
}