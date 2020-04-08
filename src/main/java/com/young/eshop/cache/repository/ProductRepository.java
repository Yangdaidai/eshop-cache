package com.young.eshop.cache.repository;

import com.young.eshop.cache.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright © 2020 YOUNG. All rights reserved.
 *
 * @Package com.young.eshop.cache.repository
 * @ClassName ProductRepository
 * @Description
 * @Author young
 * @Modify young
 * @Date 2020/4/7 17:59
 * @Version 1.0.0
 **/
@Repository
public interface ProductRepository  extends JpaRepository<Product,Integer> {

}
