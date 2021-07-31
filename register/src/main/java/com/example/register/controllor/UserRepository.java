package com.example.register.controllor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends MongoRepository<UserInfo, String> {
}
