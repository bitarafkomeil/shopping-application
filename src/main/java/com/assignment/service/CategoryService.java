package com.assignment.service;

import com.assignment.DTO.entity.create.CreateCategoryDTO;
import com.assignment.DTO.entity.read.CategoryDTO;
import com.assignment.DTO.entity.update.UpdateCategoryDTO;
import com.assignment.mapper.CategoryMapper;
import com.assignment.model.Category;
import com.assignment.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@Slf4j
public class CategoryService extends BaseService<Category, CategoryRepository,
        CreateCategoryDTO, UpdateCategoryDTO, CategoryDTO, CategoryMapper> {

    public CategoryService(CategoryRepository repository, CategoryMapper mapper, SecurityService securityService) {
        super(repository, mapper, securityService);
    }
}