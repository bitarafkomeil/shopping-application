package com.assignment.DTO.entity.read;

import com.assignment.DTO.entity.IdentifiableDTO;
import com.assignment.DTO.entity.base.BaseProductDTO;
import com.assignment.model.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link Product} entity.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends BaseProductDTO implements IdentifiableDTO,Serializable {
    @NotNull
    private Long id;

    private String categoryName;

    private Double avgRate;
}