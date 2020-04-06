package com.young.eshop.cache.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright © 2020 YOUNG. All rights reserved.
 *
 * @Package com.young.eshop.cache.configuration
 * @ClassName KafkaTopicProperties
 * @Description
 * @Author young
 * @Modify young
 * @Date 2020/3/29 16:36
 * @Version 1.0.0
 **/
@Data
@Configuration
@EnableConfigurationProperties(KafkaTopicProperties.class)
public class KafkaTopicConfiguration implements Serializable {

    private final KafkaTopicProperties properties;

    public KafkaTopicConfiguration(KafkaTopicProperties properties) {
        this.properties = properties;
    }

    @Bean
    public String[] kafkaTopicName() {
        return properties.getTopicName();
    }

    @Bean
    public String topicGroupId() {
        return properties.getGroupId();
    }
}
