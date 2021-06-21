package com.assignment.service;

import com.assignment.DTO.entity.create.CreateCommentDTO;
import com.assignment.DTO.entity.read.CommentDTO;
import com.assignment.DTO.entity.update.UpdateCommentDTO;
import com.assignment.exception.BadRequestException;
import com.assignment.exception.ErrorConstants;
import com.assignment.mapper.CommentMapper;
import com.assignment.model.Comment;
import com.assignment.model.User;
import com.assignment.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@Slf4j
public class CommentService extends BaseService<Comment, CommentRepository,
        CreateCommentDTO, UpdateCommentDTO, CommentDTO, CommentMapper> {

    public CommentService(CommentRepository repository, CommentMapper mapper, SecurityService securityService) {
        super(repository, mapper, securityService);
    }

    @Override
    protected void preCreate(Comment entity) {
        User user = securityService.getCurrentUser();
        entity.setUser(user);
    }

    @Override
    protected void preUpdate(Comment entity) {
        Comment oldComment = repository.findById(entity.getId()).orElseThrow(() -> new BadRequestException(ErrorConstants.NOT_FOUND_TYPE, "CommentId Incorrect"));
        checkEntityUser(oldComment);
        User user = securityService.getCurrentUser();
        entity.setUser(user);
    }

    @Override
    protected void preDelete(Long id) {
        Comment comment = repository.findById(id).orElseThrow(() -> new BadRequestException(ErrorConstants.NOT_FOUND_TYPE, "CommentId Incorrect"));
        checkEntityUser(comment);
    }

    private void checkEntityUser(Comment comment) {
        if (comment.getUser().getId() != securityService.getCurrentUser().getId())
            throw new AccessDeniedException("Access denied");
    }
}