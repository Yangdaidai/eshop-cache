package com.young.eshop.cache.rebuild;


import com.young.eshop.cache.model.Product;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 重建缓存的内存队列
 *
 * @author Administrator
 */
public class RebuildCacheQueue {

    private ArrayBlockingQueue<Product> queue = new ArrayBlockingQueue<>(1000);

    public void putProduct(Product product) {
        try {
            queue.put(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Product takeProduct() {
        try {
            return queue.take();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 内部单例类
     *
     * @author Administrator
     */
    private static class Singleton {

        private static RebuildCacheQueue instance;

        static {
            instance = new RebuildCacheQueue();
        }

        public static RebuildCacheQueue getInstance() {
            return instance;
        }

    }

    public static RebuildCacheQueue getInstance() {
        return Singleton.getInstance();
    }

    public static void init() {
        getInstance();
    }

}
