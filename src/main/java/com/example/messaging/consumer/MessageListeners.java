/*
 * Copyright (C) Tamedia AG 2021
 */

package com.example.messaging.consumer;

import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.example.messaging.config.MessagingConfig.QUEUE_NAME;

@RequiredArgsConstructor
@RabbitListener
@Slf4j
public class MessageListeners {


    @Queue(value = QUEUE_NAME, prefetch = 1, connection = "default-server")
    public void receiveDefault(String msg) {
        log.info("Message received on default vhost: {}", msg);
    }

    @Queue(value = QUEUE_NAME, prefetch = 1, connection = "custom-server")
    public void receiveCustom(String msg) {
        log.info("Message received on custom vhost: {}", msg);
    }
}
