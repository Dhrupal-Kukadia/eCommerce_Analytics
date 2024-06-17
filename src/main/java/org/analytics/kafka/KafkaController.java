package org.analytics.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.analytics.kafka.producer.KafkaProducerService;

@RestController
@RequestMapping("/api/analytics/kafka")
public class KafkaController {
    private final KafkaProducerService kafkaProducerService;


    @Autowired
    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("PONG");
    }

//    @PostMapping("/send")
//    public void sendMessage(@RequestBody AnalyticsLogDTO analyticsLogDTO){
//        AnalyticsLog analyticsLog = AnalyticsUtils.getLogFromDto(analyticsLogDTO);
//        kafkaProducerService.sendMessage(analyticsLog);
//        System.out.println("[KAFKA] sent log_id="+analyticsLog.getLog_id()+" logLine="+analyticsLog.getLog());
//    }
}
