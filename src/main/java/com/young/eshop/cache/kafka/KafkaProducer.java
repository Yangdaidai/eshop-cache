package com.young.eshop.cache.kafka;

import com.young.eshop.cache.configuration.KafkaTopicConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * Copyright © 2020 YOUNG. All rights reserved.
 *
 * @Package com.young.eshop.cache.kafka
 * @ClassName KafkaProducer
 * @Description
 * @Author young
 * @Modify young
 * @Date 2020/3/29 16:20
 * @Version 1.0.0
 **/
@Slf4j
@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<Integer, String> kafkaTemplate;


    public void sendMessage(String topic, String data) {
        log.info("kafka sendMessage start");
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, data);
        future.addCallback(
                success -> log.info("kafka sendMessage success topic = {}, data = {}", topic, data),
                fail -> log.error("kafka sendMessage fail, topic = {}, data = {}", topic, data)
        );

        log.info("kafka sendMessage end");
    }
}
