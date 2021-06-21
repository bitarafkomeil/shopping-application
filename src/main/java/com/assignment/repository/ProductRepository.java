package com.assignment.repository;

import com.assignment.model.Product;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for {@link Product}.
 */
@Repository
public interface ProductRepository extends BaseRepository<Product> {
}