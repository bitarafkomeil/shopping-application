package com.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Spring Data repository for the entity.
 */
@NoRepositoryBean
public interface BaseRepository<E> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {
}
