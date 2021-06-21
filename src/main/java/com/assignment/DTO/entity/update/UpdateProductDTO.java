package com.assignment.DTO.entity.update;

import com.assignment.DTO.entity.IdentifiableDTO;
import com.assignment.DTO.entity.base.BaseProductDTO;
import com.assignment.model.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Product} entity.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UpdateProductDTO extends BaseProductDTO implements Serializable, IdentifiableDTO {
    @Size(min = 2, max = 50)
    private Long id;
}