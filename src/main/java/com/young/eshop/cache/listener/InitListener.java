package com.young.eshop.cache.listener;

import com.young.eshop.cache.rebuild.RebuildCacheThread;
import com.young.eshop.cache.zookeeper.ZKDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
@Component
public class InitListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        ZKDistributedLock.init();
        log.info("ZKDistributedLock init ...");
        new Thread(new RebuildCacheThread()).start();
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

}