package com.assignment.DTO.entity.base;

import com.assignment.model.Comment;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Comment} entity.
 */
@Data
public class BaseCommentDTO implements Serializable {

    @NotNull
    @Size(min = 2, max = 50)
    private String text;

    //External
    @NotNull
    private Long productId;
}