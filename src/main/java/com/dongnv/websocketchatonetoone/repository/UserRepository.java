package com.dongnv.websocketchatonetoone.repository;

import com.dongnv.websocketchatonetoone.constant.Status;
import com.dongnv.websocketchatonetoone.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
