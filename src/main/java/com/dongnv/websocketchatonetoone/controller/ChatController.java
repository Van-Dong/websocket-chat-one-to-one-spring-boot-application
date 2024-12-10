package com.dongnv.websocketchatonetoone.controller;

import com.dongnv.websocketchatonetoone.dto.ChatNotification;
import com.dongnv.websocketchatonetoone.model.ChatMessage;
import com.dongnv.websocketchatonetoone.service.ChatMessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatController {
    ChatMessageService chatMessageService;
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")  // /app/chat
    public void processMessage(@Payload ChatMessage chatMessage) {
        ChatMessage savedMsg = chatMessageService.save(chatMessage);
        // john/queue/messages
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(),
                "/queue/messages", ChatNotification.builder()
                                .id(savedMsg.getId())
                                .senderId(chatMessage.getSenderId())
                                .recipientId(chatMessage.getRecipientId())
                                .content(chatMessage.getContent())
                        .build());  // Sẽ được gửi tới: /user/{userId}/queue/messages
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    @ResponseBody
    public List<ChatMessage> findChatMessages(@PathVariable("senderId") String senderId,
                                              @PathVariable("recipientId") String recipientId) {
        return chatMessageService.findChatMessage(senderId, recipientId);
    }
}
