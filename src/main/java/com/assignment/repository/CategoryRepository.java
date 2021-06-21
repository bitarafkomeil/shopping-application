package com.assignment.repository;

import com.assignment.model.Category;
import org.springframework.stereotype.Repository;


/**
 * Spring Data repository for {@link Category}.
 */
@Repository
public interface CategoryRepository extends BaseRepository<Category> {
}