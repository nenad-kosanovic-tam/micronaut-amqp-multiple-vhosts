package com.example.messaging.producer;

import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.example.messaging.config.MessagingConfig.EXCHANGE_NAME;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class MessageProducer {

    private static final String CUSTOM_VHOST = "custom-virtual-host";

    private final DefaultMessageClient defaultMessageClient;
    private final CustomMessageClient customMessageClient;


    public void send(final String message) {
        defaultMessageClient.send(message);
    }

    public void send(final String vHost, final String message) {
        if (CUSTOM_VHOST.equals(vHost)) {
            customMessageClient.send(message);
        } else {
            throw new IllegalArgumentException("Unknown virtual host: " + vHost);
        }
    }

    @RabbitClient(value = EXCHANGE_NAME, connection = "default-server")
    public interface DefaultMessageClient {

        @Binding("fromController")
        void send(String message);
    }

    @RabbitClient(value = EXCHANGE_NAME, connection = "custom-server")
    public interface CustomMessageClient {

        @Binding("fromController")
        void send(String message);
    }
}
