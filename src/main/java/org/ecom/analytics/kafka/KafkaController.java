package org.ecom.analytics.kafka;

import org.ecom.analytics.log.OrderLog;
import org.ecom.analytics.log.UserActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ecom.analytics.kafka.producer.KafkaProducerService;

@RestController
@RequestMapping("/analytics/kafka")
public class KafkaController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/ping")
    public String hello() {
        return "Pong";
    }

    @PostMapping("/publish/user_activity_log")
    public void publishUserActivity(@RequestBody UserActivityLog userActivityLog) {
        kafkaProducerService.publishUserActivity(userActivityLog);
    }

    @PostMapping("/publish/order_log")
    public void publishOrder(@RequestBody OrderLog orderLog) {
        kafkaProducerService.publishOrder(orderLog);
    }
}
