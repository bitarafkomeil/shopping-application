package com.assignment.mapper;

import com.assignment.DTO.entity.create.CreateUserDTO;
import com.assignment.DTO.entity.read.UserDTO;
import com.assignment.DTO.entity.update.UpdateUserDTO;
import com.assignment.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper for {@link User} and its DTOs.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends BaseMapper<CreateUserDTO, UpdateUserDTO, UserDTO, User> {

    @Override
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "block",ignore = true)
    @Mapping(target = "role",ignore = true)
    User fromCreateDTO(CreateUserDTO dto);

    @Override
    @Mapping(target = "block",ignore = true)
    @Mapping(target = "role",ignore = true)
    User fromUpdateDTO(UpdateUserDTO dto);

    @Override
    UserDTO toDto(User entity);

    @Override
    List<UserDTO> toDto(List<User> entityList);

    default User fromId(Long id) {
        if (id == null) return null;
        User user = new User();
        user.setId(id);
        return user;
    }
}