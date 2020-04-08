package com.young.eshop.cache.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.young.eshop.cache.model.Product;
import com.young.eshop.cache.repository.ProductRepository;
import com.young.eshop.cache.service.CacheService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Objects;


/**
 * 缓存Service实现类
 *
 * @author Administrator
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    public static final String CACHE_NAME = "local";
    public static final String KEY_PREFIX = "product:";

    @Resource
    private RedisTemplate<Serializable, Object> redisTemplate;

    @Resource
    private ProductRepository productRepository;

    /**
     * 将商品信息保存到本地缓存中
     *
     * @param product 商品信息
     */
    @CachePut(value = CACHE_NAME, key = "'key_'+#product.getId()")
    public void saveProduct2LocalCache(Product product) {
    }

    /**
     * 将商品信息保存到redis缓存中
     *
     * @param product 商品信息
     */
    @Override
    public void saveProduct2RedisCache(Product product) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(KEY_PREFIX + product.getId(), product);
    }

    @Override
    public Product getProductFromRedisCache(Integer id) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return (Product) operations.get(KEY_PREFIX + id);
    }

    @Override
    public Product getProduct(Integer id) {
        return productRepository.getOne(id);

    }

    /**
     * 从本地缓存中获取商品信息
     *
     * @param id id
     * @return null
     */
    @Override
    @Cacheable(value = CACHE_NAME, key = "'key_'+#id")
    public Product getLocalCache(Integer id) {
        return null;
    }


}
