package com.assignment.DTO.entity.update;

import com.assignment.DTO.entity.IdentifiableDTO;
import com.assignment.DTO.entity.base.BaseRateDTO;
import com.assignment.model.Rate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Rate} entity.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UpdateRateDTO extends BaseRateDTO implements Serializable, IdentifiableDTO {
    @Size(min = 2, max = 50)
    private Long id;
}