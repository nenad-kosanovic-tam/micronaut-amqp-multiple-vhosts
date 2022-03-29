package com.example.messaging.controller;

import com.example.messaging.model.Message;
import com.example.messaging.producer.MessageProducer;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.example.messaging.controller.MessageController.MESSAGE_PRODUCER_BASE_URI;

@Controller(MESSAGE_PRODUCER_BASE_URI)
@RequiredArgsConstructor
public class MessageController {

    public static final String MESSAGE_PRODUCER_BASE_URI = "/messages";

    private final MessageProducer messageProducer;


    @Post
    public void sendMessage(@Body Message message) {
        messageProducer.send( getMessage(message));
    }

    @Post(value = "/{vHost}")
    public void sendMessage(String vHost, @Body Message message) {
        messageProducer.send(vHost, getMessage(message));
    }

    private String getMessage(Message message) {
        return Optional.ofNullable(message).map(Message::getText).orElse("Empty message!");
    }

}
