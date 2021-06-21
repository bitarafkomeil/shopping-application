package com.assignment.DTO.entity.base;

import com.assignment.model.Rate;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link Rate} entity.
 */
@Data
public class BaseRateDTO implements Serializable {

    @NotNull
    private Short rate;

    //External
    @NotNull
    private Long productId;
}