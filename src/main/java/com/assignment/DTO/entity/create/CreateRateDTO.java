package com.assignment.DTO.entity.create;

import com.assignment.DTO.entity.base.BaseRateDTO;
import com.assignment.model.Rate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the {@link Rate} entity.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreateRateDTO extends BaseRateDTO implements Serializable {
}
