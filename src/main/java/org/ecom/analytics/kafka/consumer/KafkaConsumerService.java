package org.ecom.analytics.kafka.consumer;

import org.ecom.analytics.es.ElasticSearchService;
import org.ecom.analytics.log.OrderLog;
import org.ecom.analytics.log.UserActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @Autowired
    private ElasticSearchService esSearchService;

    @KafkaListener(topics = "user_activity", groupId = "group_id")
    public void consumeUserActivity(UserActivityLog userActivityLog) {
        System.out.println("Saving user activity " + userActivityLog + " to elasticsearch");
    }

    @KafkaListener(topics = "order", groupId = "group_id")
    public void consumeOrder(OrderLog orderLog) {
        System.out.println("Saving order log " + orderLog + " to elasticsearch");
    }
}
