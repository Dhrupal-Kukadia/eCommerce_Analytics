package org.ecom.analytics.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.ecom.analytics.log.OrderLog;

import java.util.Map;

public class OrderLogSerializer implements Serializer<OrderLog> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // no configuration needed
    }

    @Override
    public byte[] serialize(String topic, OrderLog orderLog) {
        try {
            if (orderLog == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(orderLog);
        } catch (Exception e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public byte[] serialize(String topic, Headers headers, OrderLog data) {
        return null;
    }

    @Override
    public void close() {
        // nothing to close
    }
}
