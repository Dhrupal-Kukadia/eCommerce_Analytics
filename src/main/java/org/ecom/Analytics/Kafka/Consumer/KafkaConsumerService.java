package org.ecom.Analytics.Kafka.Consumer;

import org.ecom.Analytics.ES.Service.ElasticSearchService;
import org.ecom.Analytics.Log.OrderLog;
import org.ecom.Analytics.Log.UserActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @Autowired
    private ElasticSearchService esSearchService;

    @KafkaListener(topics = "user_activity_topic", groupId = "group_id", containerFactory = "userActivityLogKafkaListenerContainerFactory")
    public void consumeUserActivity(@Payload UserActivityLog userActivityLog) {
        esSearchService.saveUserActivityLog(userActivityLog);
    }

    @KafkaListener(topics = "order_topic", groupId = "group_id", containerFactory = "orderLogKafkaListenerContainerFactory")
    public void consumeOrder(@Payload OrderLog orderLog) {
        esSearchService.saveOrderLog(orderLog);
    }
}
