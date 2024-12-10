package com.dongnv.websocketchatonetoone.controller;

import com.dongnv.websocketchatonetoone.model.User;
import com.dongnv.websocketchatonetoone.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/public")  // Không được viết /user/public ở đây, vì ta đã chỉ định /user là 1 user-specific destination rồi
    public User addUser(@Payload User user) {
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/public")
    public User disconnect(@Payload User user) {
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> findConnectedUsers() {
        return userService.findConnectedUsers();
    }
}
