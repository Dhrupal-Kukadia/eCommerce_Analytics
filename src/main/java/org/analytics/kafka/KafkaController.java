package org.analytics.kafka;

import org.analytics.log.OrderLog;
import org.analytics.log.UserActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.analytics.kafka.producer.KafkaProducerService;

@RestController
@RequestMapping("/analytics/kafka")
public class KafkaController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/publish/user_activity_log")
    public void publishUserActivity(@RequestBody UserActivityLog userActivityLog) {
        kafkaProducerService.publishUserActivity(userActivityLog);
    }

    @PostMapping("/publish/order_log")
    public void publishOrder(@RequestBody OrderLog orderLog) {
        kafkaProducerService.publishOrder(orderLog);
    }
}
