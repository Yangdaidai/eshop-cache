package com.young.eshop.cache.rebuild;

import com.young.eshop.cache.model.Product;
import com.young.eshop.cache.service.CacheService;
import com.young.eshop.cache.springcontext.SpringContext;
import com.young.eshop.cache.zookeeper.ZKDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 缓存重建线程
 *
 * @author young
 */
@Slf4j
public class RebuildCacheThread implements Runnable {


    public void run() {
        RebuildCacheQueue rebuildCacheQueue = RebuildCacheQueue.getInstance();
        ZKDistributedLock zkDistributedLock = ZKDistributedLock.getInstance();
        String prefix = ZKDistributedLock.ZK_LOCK_PATH_PREFIX;

        while (true) {
            Product product = rebuildCacheQueue.takeProduct();
            CuratorFramework client = zkDistributedLock.client;
            client.start();
            String path = prefix + product.getId();
            InterProcessMutex lock = new InterProcessMutex(client, path);

			ApplicationContext applicationContext = SpringContext.getApplicationContext();
			CacheService cacheService = applicationContext.getBean(CacheService.class);

			try {

                lock.acquire();

                Product existedProductInfo = cacheService.getProductFromRedisCache(product.getId());

                if (existedProductInfo != null) {
                    // 比较当前数据的时间版本比已有数据的时间版本是新还是旧
                    try {
                        Date date = product.getModifiedTime();
                        Date existedDate =existedProductInfo.getModifiedTime();

                        if (date.before(existedDate)) {
                            log.info("current date : {}  is before existed date : {} ", product.getModifiedTime(), existedProductInfo.getModifiedTime());
                            continue;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    log.info("current date : {}  is after existed date : {} ", product.getModifiedTime(), existedProductInfo.getModifiedTime());

                } else {
                    log.info("existed product info is null......");
                }

                cacheService.saveProduct2LocalCache(product);
                cacheService.saveProduct2RedisCache(product);

                lock.release();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

}
