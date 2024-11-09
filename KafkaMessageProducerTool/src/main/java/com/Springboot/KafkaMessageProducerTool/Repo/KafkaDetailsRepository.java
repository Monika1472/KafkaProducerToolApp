package com.Springboot.KafkaMessageProducerTool.Repo;

import com.Springboot.KafkaMessageProducerTool.Entity.KafkaDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaDetailsRepository extends JpaRepository<KafkaDetails, Integer> {
}
