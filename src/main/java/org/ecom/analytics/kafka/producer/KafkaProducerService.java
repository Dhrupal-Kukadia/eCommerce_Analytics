package org.ecom.analytics.kafka.producer;

import org.ecom.analytics.log.OrderLog;
import org.ecom.analytics.log.UserActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Value("${spring.kafka.topic.user-activity}")
    private String userActivityTopic;
    @Value("${spring.kafka.topic.order}")
    private String orderTopic;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishUserActivity(UserActivityLog userActivityLog) {
        System.out.println("Sending user activity log " + userActivityLog + " to kafka");
        kafkaTemplate.send(userActivityTopic, userActivityLog);
    }

    public void publishOrder(OrderLog orderLog) {
        System.out.println("Sending order log " + orderLog + " to kafka");
        kafkaTemplate.send(orderTopic, orderLog);
    }
}
