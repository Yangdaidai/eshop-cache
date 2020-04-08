package com.young.eshop.cache.controller;

import com.young.eshop.cache.model.Product;
import com.young.eshop.cache.rebuild.RebuildCacheQueue;
import com.young.eshop.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;


/**
 * 缓存Controller
 *
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("cache")
public class CacheController {

    @Resource
    private CacheService cacheService;

    @RequestMapping("/testPutCache")
    public String testPutCache(Product productInfo) {
        cacheService.saveProduct2LocalCache(productInfo);
        return "success";
    }

    @RequestMapping("/testGetCache")
    public Product testGetCache(Integer id) {
        return cacheService.getLocalCache(id);
    }

    @RequestMapping("/getProduct")
    public Product getProduct(Integer id) {
        Product product = null;
        product = cacheService.getProductFromRedisCache(id);
        log.info("product getProductFromRedisCache : {}", product);

        if (Objects.isNull(product)) {
            product = cacheService.getLocalCache(id);
            log.info("product getLocalCache : {}", product);
        }
        if (Objects.isNull(product)) {
            // 需要从数据源重新拉去数据，重建缓存
            product = cacheService.getProduct(id);
            log.info("product getDataBase : {}", product);

            RebuildCacheQueue rebuildCacheQueue = RebuildCacheQueue.getInstance();
            rebuildCacheQueue.putProduct(product);
        }
        return product;
    }

}
