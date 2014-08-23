package com.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.domain.User;

public interface UserRepository extends MongoRepository<User, String>{
}
