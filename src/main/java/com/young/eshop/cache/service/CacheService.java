package com.young.eshop.cache.service;

import com.young.eshop.cache.model.ProductInfo;

/**
 * 缓存service接口
 *
 * @author Administrator
 */
public interface CacheService {

    /**
     * 将商品信息保存到本地缓存中
     *
     * @param productInfo 商品信息
     */
     void saveLocalCache(ProductInfo productInfo);

    /**
     * 从本地缓存中获取商品信息
     *
     * @param id id
     * @return com.young.eshop.cache.model.ProductInfo
     */
     ProductInfo getLocalCache(Long id);

}
