package com.assignment.security;

import com.assignment.repository.UserRepository;
import com.assignment.exception.BadRequestException;
import com.assignment.exception.ErrorConstants;
import com.assignment.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class PasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    private Authentication createAuthentication(User user) {
        String userName = user.getUserName();
        return new UsernamePasswordAuthenticationToken(userName, null,
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String userName = token.getPrincipal().toString();
        String password = token.getCredentials().toString();
        return userRepository.findByUserNameAndPasswordAndBlockIsFalse(userName, password)
                .map(this::createAuthentication)
                .orElseThrow(() -> new BadRequestException(ErrorConstants.NOT_FOUND_TYPE, "UserName Or Password Incorrect"));
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
}