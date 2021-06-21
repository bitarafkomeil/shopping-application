package com.assignment.mapper;

import com.assignment.DTO.entity.create.CreateCommentDTO;
import com.assignment.DTO.entity.read.CommentDTO;
import com.assignment.DTO.entity.update.UpdateCommentDTO;
import com.assignment.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper for {@link Comment} and its DTOs.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class, UserMapper.class})
public interface CommentMapper extends BaseMapper<CreateCommentDTO, UpdateCommentDTO, CommentDTO, Comment> {

    @Override
    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "userId",ignore = true)
    Comment fromCreateDTO(CreateCommentDTO dto);

    @Override
    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "userId",ignore = true)
    Comment fromUpdateDTO(UpdateCommentDTO dto);

    @Override
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.userName", target = "userName")
    CommentDTO toDto(Comment entity);

    @Override
    List<CommentDTO> toDto(List<Comment> entityList);

    default Comment fromId(Long id) {
        if (id == null) return null;
        Comment comment = new Comment();
        comment.setId(id);
        return comment;
    }
}