package com.assignment.DTO.entity.create;

import com.assignment.DTO.entity.base.BaseCommentDTO;
import com.assignment.model.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the {@link Comment} entity.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreateCommentDTO extends BaseCommentDTO implements Serializable {
}