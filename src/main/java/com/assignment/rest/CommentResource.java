package com.assignment.rest;

import com.assignment.DTO.entity.create.CreateCommentDTO;
import com.assignment.DTO.entity.read.CommentDTO;
import com.assignment.DTO.entity.update.UpdateCommentDTO;
import com.assignment.mapper.CommentMapper;
import com.assignment.model.Comment;
import com.assignment.repository.CommentRepository;
import com.assignment.service.CommentService;
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
@RequestMapping("/api/comments")
@Api(tags = "comments")
@Slf4j
public class CommentResource extends BaseResource<Comment, CommentRepository, CreateCommentDTO, UpdateCommentDTO, CommentDTO, CommentMapper, CommentService> {

    public CommentResource(CommentService service) {
        super(service);
    }

    @PostMapping("")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CreateCommentDTO createCommentDTO) {
        return createEntity(createCommentDTO);
    }

    @PutMapping("")
    public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody UpdateCommentDTO updateCommentDTO) {
        return updateEntity(updateCommentDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable Long id) {
        return getEntity(id);
    }

    @GetMapping("")
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        return getAllEntities();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        return deleteEntity(id);
    }
}