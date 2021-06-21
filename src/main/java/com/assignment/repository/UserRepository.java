package com.assignment.repository;

import com.assignment.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data repository for {@link User}.
 */
@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUserName(String userName);

    Optional<User> findByUserNameAndPasswordAndBlockIsFalse(String userName, String password);

}