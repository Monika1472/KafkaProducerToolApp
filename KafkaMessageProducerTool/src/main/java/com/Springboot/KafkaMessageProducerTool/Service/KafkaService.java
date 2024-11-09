package com.Springboot.KafkaMessageProducerTool.Service;

import com.Springboot.KafkaMessageProducerTool.Entity.KafkaDetails;
import com.Springboot.KafkaMessageProducerTool.Repo.KafkaDetailsRepository;
import com.Springboot.KafkaMessageProducerTool.Repo.MessageTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaService {
    private final KafkaDetailsRepository kafkaDetailsRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaService(
            KafkaDetailsRepository kafkaDetailsRepository,
            KafkaTemplate<String, String> kafkaTemplate
    ) {
        this.kafkaDetailsRepository = kafkaDetailsRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public KafkaDetails saveDetails(KafkaDetails kafkaDetails){
        return kafkaDetailsRepository.save(kafkaDetails);
    }
    public List<KafkaDetails> getAll(){
        return kafkaDetailsRepository.findAll();
    }
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

}
