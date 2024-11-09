package com.Springboot.KafkaMessageProducerTool.Controller;

import com.Springboot.KafkaMessageProducerTool.Entity.KafkaDetails;
import com.Springboot.KafkaMessageProducerTool.Service.KafkaAdminService;
import com.Springboot.KafkaMessageProducerTool.Service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private final KafkaService kafkaService;

    @Autowired
    @Lazy
    private final KafkaAdminService kafkaAdminService;

    @Autowired
    public KafkaController(KafkaService kafkaService, KafkaAdminService kafkaAdminService) {
        this.kafkaService = kafkaService;
        this.kafkaAdminService = kafkaAdminService;
    }
    @GetMapping("/admin/inbuiltAllTopics")
    public List<String> getAllInbuiltTopics() {
        return kafkaAdminService.getAllTopics();
    }
    @GetMapping("/user/topics")
    public List<KafkaDetails> getAllTopics() {
        return kafkaService.getAll();
        //return kafkaAdminService.getAllTopics();
    }

    @PostMapping("/admin/create-topic")
    public String createTopic(@RequestBody KafkaDetails kafkaDetails) {
        kafkaService.saveDetails(kafkaDetails);
        kafkaAdminService.createTopic(kafkaDetails.getTopic_name(), kafkaDetails.getPartitions(), kafkaDetails.getReplication_factor());
        return("Topic created successfully!");
    }

    @PostMapping("user/publish/{topicName}")
    public ResponseEntity<String> publishMessage(@PathVariable String topicName,
                                                 @RequestBody String message) {
        try {
            String decodedMessage = URLDecoder.decode(message, "UTF-8");
            kafkaService.sendMessage(topicName, decodedMessage);
            return ResponseEntity.ok("Message sent to the topic: " + topicName);
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(500).body("Error decoding the message");
        }
    }
}