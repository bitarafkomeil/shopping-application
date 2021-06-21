package com.assignment.DTO.entity.create;

import com.assignment.DTO.entity.base.BaseProductDTO;
import com.assignment.model.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the {@link Product} entity.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreateProductDTO extends BaseProductDTO implements Serializable {
}