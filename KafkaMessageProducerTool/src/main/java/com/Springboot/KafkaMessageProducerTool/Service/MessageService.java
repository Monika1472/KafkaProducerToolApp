package com.Springboot.KafkaMessageProducerTool.Service;

import com.Springboot.KafkaMessageProducerTool.Entity.MessageTemplate;
import com.Springboot.KafkaMessageProducerTool.Repo.MessageTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageTemplateRepository messageTemplateRepository;

    @Autowired
    public MessageService(MessageTemplateRepository messageTemplateRepository) {
        this.messageTemplateRepository = messageTemplateRepository;
    }

    public List<MessageTemplate> getAllMessageTemplates() {
        return messageTemplateRepository.findAll();
    }

    public MessageTemplate getMessageTemplateById(Long id) {
        Optional<MessageTemplate> optionalMessageTemplate = messageTemplateRepository.findById(id);
        return optionalMessageTemplate.orElse(null);
    }

    public MessageTemplate saveMessageTemplate(MessageTemplate messageTemplate) {
        return messageTemplateRepository.save(messageTemplate);
    }

    public void deleteMessageTemplate(Long id) {
        messageTemplateRepository.deleteById(id);
    }

    public MessageTemplate updateMessageTemplate(Long id, MessageTemplate updatedTemplate) {
        Optional<MessageTemplate> optionalMessageTemplate = messageTemplateRepository.findById(id);
        if (optionalMessageTemplate.isPresent()) {
            MessageTemplate existingTemplate = optionalMessageTemplate.get();
            existingTemplate.setMessage_title(updatedTemplate.getMessage_title());
            existingTemplate.setMessage_desc(updatedTemplate.getMessage_desc());
            existingTemplate.setTopic_name(updatedTemplate.getTopic_name());
            existingTemplate.setMessage_content(updatedTemplate.getMessage_content());
            existingTemplate.setPlaceholders(updatedTemplate.getPlaceholders());
            existingTemplate.setNumber_of_placeholders(updatedTemplate.getNumber_of_placeholders());
            return messageTemplateRepository.save(existingTemplate);
        } else {
            return null;
        }
    }
}
