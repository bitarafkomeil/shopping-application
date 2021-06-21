package com.assignment.mapper;

import com.assignment.DTO.entity.create.CreateCategoryDTO;
import com.assignment.DTO.entity.read.CategoryDTO;
import com.assignment.DTO.entity.update.UpdateCategoryDTO;
import com.assignment.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper for {@link Category} and its DTOs.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper extends BaseMapper<CreateCategoryDTO, UpdateCategoryDTO, CategoryDTO, Category> {

    @Override
    @Mapping(target = "id",ignore = true)
    Category fromCreateDTO(CreateCategoryDTO dto);

    @Override
    Category fromUpdateDTO(UpdateCategoryDTO dto);

    @Override
    CategoryDTO toDto(Category entity);

    @Override
    List<CategoryDTO> toDto(List<Category> entityList);

    default Category fromId(Long id) {
        if (id == null) return null;
        Category category = new Category();
        category.setId(id);
        return category;
    }
}