package com.Springboot.KafkaMessageProducerTool.Repo;

import com.Springboot.KafkaMessageProducerTool.Entity.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {
}
