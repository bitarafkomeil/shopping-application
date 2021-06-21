package com.assignment.service;

import com.assignment.repository.UserRepository;
import com.assignment.exception.BadRequestException;
import com.assignment.exception.ErrorConstants;
import com.assignment.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class SecurityService {

    private final UserRepository repository;


    public User getCurrentUser() {
        return getUser().orElseThrow(() -> new BadRequestException(ErrorConstants.NOT_FOUND_TYPE, "JWT Token Incorrect"));
    }

    public Optional<User> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principal = authentication.getPrincipal().toString();
        return repository.findByUserName(principal);
    }
}