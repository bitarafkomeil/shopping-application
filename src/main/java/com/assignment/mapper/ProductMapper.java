package com.assignment.mapper;

import com.assignment.DTO.entity.create.CreateProductDTO;
import com.assignment.DTO.entity.read.ProductDTO;
import com.assignment.DTO.entity.update.UpdateProductDTO;
import com.assignment.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper for {@link Product} and its DTOs.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper extends BaseMapper<CreateProductDTO, UpdateProductDTO, ProductDTO, Product> {

    @Override
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "avgRate",ignore = true)
    Product fromCreateDTO(CreateProductDTO dto);

    @Override
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "avgRate",ignore = true)
    Product fromUpdateDTO(UpdateProductDTO dto);

    @Override
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductDTO toDto(Product entity);

    @Override
    List<ProductDTO> toDto(List<Product> entityList);

    default Product fromId(Long id) {
        if (id == null) return null;
        Product product = new Product();
        product.setId(id);
        return product;
    }
}