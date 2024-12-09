package com.dongnv.websocketchatonetoone.repository;

import com.dongnv.websocketchatonetoone.constant.Status;
import com.dongnv.websocketchatonetoone.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
