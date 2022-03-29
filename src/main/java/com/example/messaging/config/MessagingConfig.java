/*
 * Copyright (C) Tamedia AG 2022
 */

package com.example.messaging.config;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import io.micronaut.rabbitmq.connect.ChannelInitializer;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.util.Map;

@Singleton
public class MessagingConfig extends ChannelInitializer {

    public static final String EXCHANGE_NAME = "exchange-name";
    public static final String QUEUE_NAME = "queue-name";

    @Override
    public void initialize(Channel channel, String connectionName) throws IOException {
        //Here we can use connectionName to crete specific setup foreach virtual host.

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, true, false, Map.of());
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "fromController");
    }

}
