package com.young.eshop.cache.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.TimeUnit;

/**
 * Copyright © 2020 YOUNG. All rights reserved.
 *
 * @Package com.young.eshop.cache.zookeeper
 * @ClassName ZKDistributedLock
 * @Description zookeeper 分布式锁
 * @Author young
 * @Modify young
 * @Date 2020/3/30 17:56
 * @Version 1.0.0
 **/
public class ZKDistributedLock {

    private static final String ZK_ADDRESS = "eshop-cache01:2181,eshop-cache02:2181,eshop-cache03:2181";
    private static final String ZK_LOCK_PATH_PREFIX = "/product-lock-";
    private  CuratorFramework client;
    public ZKDistributedLock() {
        RetryPolicy retryPolicy=new RetryNTimes(10, 5000);
        this.client=CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
              retryPolicy
      );
    }

    private static class Singleton {

        private static ZKDistributedLock instance;

        static {
            instance = new ZKDistributedLock();
        }

        public static ZKDistributedLock getInstance() {
            return instance;
        }

    }


    public static ZKDistributedLock getInstance() {
        return Singleton.getInstance();
    }


    public static void init() {
        getInstance();
    }

    public static void main(String[] args) {
        RetryPolicy retry=new RetryNTimes(10, 5000);
        CuratorFramework client = getClient(ZK_ADDRESS,retry);
        client.start();
        try {
            String path = client.create().withMode(CreateMode.EPHEMERAL).forPath("/path");
            Stat stat = client.checkExists().forPath("/path");
            if (stat == null) {
                String path1 = client.create().withMode(CreateMode.EPHEMERAL).forPath("/path");
            }
            client.delete().forPath("/path");

        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread t1 = new Thread(() -> {
            doWithLock(client, ZK_LOCK_PATH_PREFIX + "110");
        }, "t1");
        Thread t2 = new Thread(() -> {
            doWithLock(client, ZK_LOCK_PATH_PREFIX + "110");
        }, "t2");

        t1.start();
        t2.start();
    }

    public static CuratorFramework getClient(String address, RetryPolicy retryPolicy) {
        return CuratorFrameworkFactory.newClient(
                address,
                retryPolicy
        );
    }


    public static void doWithLock(CuratorFramework client, String path) {
        InterProcessMutex lock = new InterProcessMutex(client, path);

        try {
            if (lock.acquire(10 * 1000, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " hold lock");
                Thread.sleep(5000L);
                System.out.println(Thread.currentThread().getName() + " release lock");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}

