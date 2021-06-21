package com.assignment.DTO.entity.read;

import com.assignment.DTO.entity.IdentifiableDTO;
import com.assignment.DTO.entity.base.BaseCommentDTO;
import com.assignment.model.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Comment} entity.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CommentDTO extends BaseCommentDTO implements IdentifiableDTO,Serializable {

    @Size(min = 2, max = 50)
    private Long id;

    private String productName;

    @NotNull
    private Long userId;

    private String userName;
}