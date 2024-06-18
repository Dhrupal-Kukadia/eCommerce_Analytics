package org.ecom.analytics.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.ecom.analytics.kafka.consumer.OrderLogDeserializer;
import org.ecom.analytics.kafka.consumer.UserActivityLogDeserializer;
import org.ecom.analytics.log.OrderLog;
import org.ecom.analytics.log.UserActivityLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;
    private final String groupId = "group_id";

    @Bean
    public ConsumerFactory<String, UserActivityLog> userActivityLogConsumerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, String.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, UserActivityLogDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configs);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserActivityLog> userActivityLogConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserActivityLog> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userActivityLogConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, OrderLog> orderLogConsumerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, String.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, OrderLogDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configs);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderLog> orderLogConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderLog> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderLogConsumerFactory());
        return factory;
    }
}
