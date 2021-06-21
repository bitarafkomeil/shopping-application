package com.assignment.DTO.entity.update;

import com.assignment.DTO.entity.IdentifiableDTO;
import com.assignment.DTO.entity.base.BaseCategoryDTO;
import com.assignment.model.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UpdateCategoryDTO extends BaseCategoryDTO implements Serializable, IdentifiableDTO {
    @Size(min = 2, max = 50)
    private Long id;
}