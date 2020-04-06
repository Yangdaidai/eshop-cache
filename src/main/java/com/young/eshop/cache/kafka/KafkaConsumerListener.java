package com.young.eshop.cache.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * kafka消费者
 *
 * @author Administrator
 */
@Slf4j
@Component
public class KafkaConsumerListener {

    @KafkaListener(topics = "#{kafkaTopicName}", groupId = "#{topicGroupId}")
    public void receiveMessage(ConsumerRecord<String, String> record) {
        String message = record.value();
        if (Objects.nonNull(message)) {
            log.info("------------------ receiveMessage =" + message);
        }
    }


}
