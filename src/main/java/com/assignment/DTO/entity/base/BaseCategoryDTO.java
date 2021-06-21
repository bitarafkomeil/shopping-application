package com.assignment.DTO.entity.base;

import com.assignment.model.Category;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity.
 */
@Data
public class BaseCategoryDTO implements Serializable {

    @NotNull
    @Size(min = 2, max = 50)
    private String name;
}