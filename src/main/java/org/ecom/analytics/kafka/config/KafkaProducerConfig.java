package org.ecom.analytics.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.ecom.analytics.kafka.producer.OrderLogSerializer;
import org.ecom.analytics.kafka.producer.UserActivityLogSerializer;
import org.ecom.analytics.log.OrderLog;
import org.ecom.analytics.log.UserActivityLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaProducerConfig {
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, UserActivityLog> userActivityLogProducerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, String.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UserActivityLogSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, UserActivityLog> userActivityLogKafkaTemplate() {
        return new KafkaTemplate<>(userActivityLogProducerFactory());
    }

    @Bean
    public ProducerFactory<String, OrderLog> orderLogProducerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, String.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, OrderLogSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, OrderLog> orderLogKafkaTemplate() {
        return new KafkaTemplate<>(orderLogProducerFactory());
    }
}
