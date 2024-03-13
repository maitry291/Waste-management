package com.distritubuteddatabase.authenticationservice.repository;

import com.distritubuteddatabase.authenticationservice.model.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends MongoRepository<AuthUser, String> {
    Optional<AuthUser> findByUserName(String userName);
}
