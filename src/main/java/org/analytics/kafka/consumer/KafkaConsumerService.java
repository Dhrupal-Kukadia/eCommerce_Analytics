package org.analytics.kafka.consumer;

import org.analytics.es.ElasticSearchRepository;
import org.analytics.log.OrderLog;
import org.analytics.log.UserActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @Autowired
    private ElasticSearchRepository esSearchRepository;

    @KafkaListener(topics = "user_activity", groupId = "group_id")
    public void consumeUserActivity(UserActivityLog userActivityLog) {

    }

    @KafkaListener(topics = "order", groupId = "group_id")
    public void consumeOrder(OrderLog orderLog) {

    }
}
