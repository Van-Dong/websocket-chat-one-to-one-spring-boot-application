package com.dongnv.websocketchatonetoone.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {
    @Id
    String id;
    String chatId;
    String senderId;
    String recipientId;
    String content;
    Date timestamp;  // Tự động thêm vào cơ sở dữ liệu luôn (chứ không cần gán đâu)
}
