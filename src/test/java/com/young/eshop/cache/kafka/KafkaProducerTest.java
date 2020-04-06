package com.young.eshop.cache.kafka;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;



@SpringBootTest
@RunWith(SpringRunner.class)
class KafkaProducerTest {

    @Resource
    private KafkaProducer producer;

    @Test
    void sendMessage() {
        producer.sendMessage("topic1","test receive by consumerRecord ! ");
    }
}