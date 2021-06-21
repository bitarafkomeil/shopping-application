package com.assignment.DTO.entity.base;

import com.assignment.model.Product;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Product} entity.
 */
@Data
public class BaseProductDTO implements Serializable {

    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    private Long price;

    @NotNull
    private Long categoryId;
}