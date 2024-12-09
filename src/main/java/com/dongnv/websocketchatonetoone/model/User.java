package com.dongnv.websocketchatonetoone.model;

import com.dongnv.websocketchatonetoone.constant.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    String nickName;
    String fullName;
    Status status;
}
