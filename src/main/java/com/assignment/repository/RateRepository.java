package com.assignment.repository;

import com.assignment.model.Rate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data repository for {@link Rate}.
 */
@Repository
public interface RateRepository extends BaseRepository<Rate> {
    Optional<Rate> findByProductIdAndUserId(Long productId, Long userId);
    List<Rate> findAllByProductId(Long productId);
}