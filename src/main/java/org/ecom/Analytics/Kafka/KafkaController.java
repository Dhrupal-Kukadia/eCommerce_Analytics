package org.ecom.Analytics.Kafka;

import org.ecom.Analytics.Log.OrderLog;
import org.ecom.Analytics.Log.UserActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ecom.Analytics.Kafka.Producer.KafkaProducerService;

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
