package org.analytics.kafka.producer;

import org.analytics.log.OrderLog;
import org.analytics.log.UserActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final String USER_ACTIVITY_TOPIC = "user_activity_log";
    private static final String ORDER_LOG_TOPIC = "order_log";
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishUserActivity(UserActivityLog userActivityLog) {
        kafkaTemplate.send(USER_ACTIVITY_TOPIC, userActivityLog);
    }

    public void publishOrder(OrderLog orderLog) {
        kafkaTemplate.send(ORDER_LOG_TOPIC, orderLog);
    }
}
