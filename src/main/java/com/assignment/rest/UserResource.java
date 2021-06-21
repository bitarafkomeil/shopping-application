package com.assignment.rest;

import com.assignment.DTO.entity.create.CreateUserDTO;
import com.assignment.DTO.entity.read.UserDTO;
import com.assignment.DTO.entity.update.UpdateUserDTO;
import com.assignment.DTO.request.LoginRequest;
import com.assignment.DTO.response.JwtResponse;
import com.assignment.mapper.UserMapper;
import com.assignment.model.User;
import com.assignment.repository.UserRepository;
import com.assignment.security.JwtFilter;
import com.assignment.security.PasswordAuthenticationService;
import com.assignment.security.TokenProvider;
import com.assignment.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * REST controller for managing customer.
 */
@RestController
@RequestMapping("/api/users")
@Api(tags = "users")
@Slf4j
public class UserResource extends BaseResource<User, UserRepository, CreateUserDTO, UpdateUserDTO, UserDTO, UserMapper, UserService> {

    private final TokenProvider tokenProvider;
    private final PasswordAuthenticationService passwordAuthenticationService;

    public UserResource(UserService service, TokenProvider tokenProvider, PasswordAuthenticationService passwordAuthenticationService) {
        super(service);
        this.tokenProvider = tokenProvider;
        this.passwordAuthenticationService = passwordAuthenticationService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> signIn(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = passwordAuthenticationService.login(request);
        return getJwtResponseEntity(authentication);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> signUp(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return createEntity(createUserDTO);
    }

    @PutMapping("/{id}/block")
    public ResponseEntity<UserDTO> blockUser(@PathVariable Long id) {
        UserDTO userDTO = service.blockUser(id);
        return ResponseEntity.ok().body(userDTO);
    }

    @PutMapping("/{id}/unblock")
    public ResponseEntity<UserDTO> unblockUser(@PathVariable Long id) {
        UserDTO userDTO = service.unblockUser(id);
        return ResponseEntity.ok().body(userDTO);
    }

    public ResponseEntity<JwtResponse> getJwtResponseEntity(Authentication authentication) {
        String jwt = tokenProvider.createToken(authentication);
        return ResponseEntity.ok()
                .header(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt)
                .body(new JwtResponse(jwt, tokenProvider.getRole(jwt), tokenProvider.getUserName(jwt), tokenProvider.getUserId(jwt)));
    }
}