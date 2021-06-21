package com.assignment.service;

import com.assignment.DTO.entity.create.CreateUserDTO;
import com.assignment.DTO.entity.read.UserDTO;
import com.assignment.DTO.entity.update.UpdateUserDTO;
import com.assignment.exception.BadRequestException;
import com.assignment.exception.ErrorConstants;
import com.assignment.mapper.UserMapper;
import com.assignment.model.User;
import com.assignment.model.enumeration.UserRole;
import com.assignment.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional(readOnly = true)
@Slf4j
public class UserService extends BaseService<User, UserRepository,
        CreateUserDTO, UpdateUserDTO, UserDTO, UserMapper> {

    public UserService(UserRepository repository, UserMapper mapper, SecurityService securityService) {
        super(repository, mapper, securityService);
    }

    @Override
    protected void preCreate(User entity) {
        entity.setBlock(Boolean.FALSE);
        entity.setRole(UserRole.USER);
    }

    @Transactional
    public UserDTO blockUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new BadRequestException(ErrorConstants.NOT_FOUND_TYPE, "UserId Incorrect"));
        user.setBlock(Boolean.TRUE);
        user = repository.save(user);
        return mapper.toDto(user);
    }

    @Transactional
    public UserDTO unblockUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new BadRequestException(ErrorConstants.NOT_FOUND_TYPE, "UserId Incorrect"));
        user.setBlock(Boolean.FALSE);
        user = repository.save(user);
        return mapper.toDto(user);
    }
}