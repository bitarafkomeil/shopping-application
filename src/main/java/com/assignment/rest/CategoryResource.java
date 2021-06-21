package com.assignment.rest;

import com.assignment.DTO.entity.create.CreateCategoryDTO;
import com.assignment.DTO.entity.read.CategoryDTO;
import com.assignment.DTO.entity.update.UpdateCategoryDTO;
import com.assignment.mapper.CategoryMapper;
import com.assignment.model.Category;
import com.assignment.repository.CategoryRepository;
import com.assignment.service.CategoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * REST controller for managing rate.
 */
@RestController
@RequestMapping("/api/categories")
@Api(tags = "categories")
@Slf4j
public class CategoryResource extends BaseResource<Category, CategoryRepository, CreateCategoryDTO, UpdateCategoryDTO, CategoryDTO, CategoryMapper, CategoryService> {

    public CategoryResource(CategoryService service) {
        super(service);
    }

    @PostMapping("")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CreateCategoryDTO createCategoryDTO) {
        return createEntity(createCategoryDTO);
    }

    @PutMapping("")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody UpdateCategoryDTO updateCategoryDTO) {
        return updateEntity(updateCategoryDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
        return getEntity(id);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return getAllEntities();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        return deleteEntity(id);
    }
}