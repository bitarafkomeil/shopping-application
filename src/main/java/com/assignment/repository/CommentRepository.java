package com.assignment.repository;

import com.assignment.model.Comment;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for {@link Comment}.
 */
@Repository
public interface CommentRepository extends BaseRepository<Comment> {
}