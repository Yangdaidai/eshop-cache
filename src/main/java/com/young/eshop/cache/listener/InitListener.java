package com.young.eshop.cache.listener;

import com.young.eshop.cache.rebuild.RebuildCacheThread;
import com.young.eshop.cache.zookeeper.ZKDistributedLock;
import com.young.eshop.cache.zookeeper.ZooKeeperSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Copyright © 2020 YOUNG. All rights reserved.
 *
 * @Package com.young.eshop.cache.listener
 * @ClassName InitListener
 * @Description
 * @Author young
 * @Modify young
 * @Date 2020/4/5 23:24
 * @Version 1.0.0
 **/
@Slf4j
public class InitListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        new Thread(new RebuildCacheThread()).start();
        ZKDistributedLock.init();
        log.info("ZKDistributedLock init ...");
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

}