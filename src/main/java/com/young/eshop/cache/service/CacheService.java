package com.young.eshop.cache.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.young.eshop.cache.model.Product;

/**
 * 缓存service接口
 *
 * @author Administrator
 */
public interface CacheService {



    /**
     * 从本地缓存中获取商品信息
     *
     * @param id id
     * @return com.young.eshop.cache.model.ProductInfo
     */
     Product getLocalCache(Integer id);

    /**
     * 将商品信息保存到本地缓存中
     *
     * @param product 商品信息
     */
    void saveProduct2LocalCache(Product product);

    /**
     * 将商品信息保存到redis缓存中
     *
     * @param product 商品信息
     */
    void saveProduct2RedisCache(Product product);

    /**
     * 从redis缓存获取商品信息
     *
     * @param id 商品信息
     */
    Product getProductFromRedisCache(Integer id)  ;

    Product getProduct(Integer id);

}
