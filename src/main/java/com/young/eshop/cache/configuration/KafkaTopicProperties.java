package com.young.eshop.cache.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * Copyright © 2020 YOUNG. All rights reserved.
 *
 * @Package com.young.eshop.cache.configuration
 * @ClassName KafkaTopicProperties
 * @Description
 * @Author young
 * @Modify young
 * @Date 2020/3/29 19:57
 * @Version 1.0.0
 **/
@Data
@ConfigurationProperties("kafka.topic")
public class KafkaTopicProperties implements Serializable {
    private String groupId;
    private String[] topicName;
}
