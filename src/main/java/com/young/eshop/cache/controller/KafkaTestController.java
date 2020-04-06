package com.young.eshop.cache.controller;

import com.young.eshop.cache.configuration.KafkaTopicConfiguration;
import com.young.eshop.cache.kafka.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Copyright © 2020 YOUNG. All rights reserved.
 *
 * @Package com.young.eshop.cache.controller
 * @ClassName KafkaTestController
 * @Description
 * @Author young
 * @Modify young
 * @Date 2020/3/29 17:30
 * @Version 1.0.0
 **/
@Slf4j
@RestController
public class KafkaTestController {

    @Resource
    private KafkaTopicConfiguration kafkaTopicConfig;

    @Resource
    private KafkaProducer producer;

    @RequestMapping("/testSendMsg")
    public String testSendMsg() {
        String[] topicNames = kafkaTopicConfig.kafkaTopicName();
        String topic = topicNames[0];
        String data="good luck !";
        producer.sendMessage(topic, data);
        log.info(" send message = {} ",data);
        return "success";
    }

}
