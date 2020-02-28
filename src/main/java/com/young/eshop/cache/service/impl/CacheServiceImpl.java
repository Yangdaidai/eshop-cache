package com.young.eshop.cache.service.impl;

import com.young.eshop.cache.model.ProductInfo;
import com.young.eshop.cache.service.CacheService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * 缓存Service实现类
 *
 * @author Administrator
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    public static final String CACHE_NAME = "local";

    /**
     * 将商品信息保存到本地缓存中
     *
     * @param productInfo 商品信息
     */
    @CachePut(value = CACHE_NAME, key = "'key_'+#productInfo.getId()")
    public void saveLocalCache(ProductInfo productInfo) {
    }

    /**
     * 从本地缓存中获取商品信息
     *
     * @param id id
     * @return null
     */
    @Cacheable(value = CACHE_NAME, key = "'key_'+#id")
    public ProductInfo getLocalCache(Long id) {
        return null;
    }

}
