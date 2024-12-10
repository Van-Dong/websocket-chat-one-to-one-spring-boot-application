package com.dongnv.websocketchatonetoone.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatNotification {
    String id;
    String senderId;
    String recipientId;
    String content;
}
