package org.ecom.analytics.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.ecom.analytics.log.OrderLog;

import java.nio.ByteBuffer;
import java.util.Map;

public class OrderLogDeserializer implements Deserializer<OrderLog> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // no configuration needed
    }

    @Override
    public OrderLog deserialize(String topic, byte[] bytes) {
        try {
            if (bytes == null || bytes.length == 0) {
                return null;
            }
            return objectMapper.readValue(bytes, OrderLog.class);
        } catch (Exception e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public OrderLog deserialize(String topic, Headers headers, byte[] data) {
        return null;
    }

    @Override
    public OrderLog deserialize(String topic, Headers headers, ByteBuffer data) {
        return null;
    }

    @Override
    public void close() {
        // nothing to close
    }
}
