package com.assignment.DTO.entity.create;

import com.assignment.DTO.entity.base.BaseCategoryDTO;
import com.assignment.model.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreateCategoryDTO extends BaseCategoryDTO implements Serializable {
}
