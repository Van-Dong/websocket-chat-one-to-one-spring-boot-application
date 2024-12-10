package com.dongnv.websocketchatonetoone.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/user");  // Gửi tin nhắn đến các subscriber đã đăng ký
        registry.setApplicationDestinationPrefixes("/app");  // tin nhắn từ client đến server, được xử lý trong controller ứng dụng
        registry.setUserDestinationPrefix("/user");  // gửi tin nhắn đến người dùng cụ thể từ server (có phương thức gửi nó sẽ dạng /users/{userId}/...)
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .withSockJS();
    }


    // Giúp serialize và deserlialization message
    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver(); // Giúp xác định content type của message khi gửi và nhận
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);  // hệ thống mặc định cho rằng các message dùng JSON, trừ khi có content type khác được chỉ định

        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter(); // converter từ JSON sang object java và ngược lại
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver); // gắn resolver (vừa tạo ở trên) vào converter
        messageConverters.add(converter);  // Thêm converter vào danh sách message converters của hệ thống

        return false;  // false ->  không muốn Spring tự động cấu hình thêm các MessageConverter khác (chỉ dùng converter mà ta đã cấu hình)
    }
}
