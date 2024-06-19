package org.ecom.WebAnalytics.Kafka.Producer;

import org.ecom.WebAnalytics.Log.OrderLog;
import org.ecom.WebAnalytics.Log.UserActivityLog;
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
    private KafkaTemplate<String, UserActivityLog> userActivityLogKafkaTemplate;
    @Autowired
    private KafkaTemplate<String, OrderLog> orderLogKafkaTemplate;

    public void publishUserActivity(UserActivityLog userActivityLog) {
        userActivityLogKafkaTemplate.send(userActivityTopic, userActivityLog);
    }

    public void publishOrder(OrderLog orderLog) {
        orderLogKafkaTemplate.send(orderTopic, orderLog);
    }
}
