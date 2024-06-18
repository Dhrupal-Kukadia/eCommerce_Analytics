package org.ecom.analytics.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.ecom.analytics.log.UserActivityLog;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class UserActivityLogSerializer implements Serializer<UserActivityLog> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // no configuration needed
    }

    @Override
    public byte[] serialize(String topic, UserActivityLog userActivityLog) {
        try {
            if (userActivityLog == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(userActivityLog);
        } catch (Exception e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public byte[] serialize(String topic, Headers headers, UserActivityLog data) {
        return null;
    }

    @Override
    public void close() {
        // nothing to close
    }
}
