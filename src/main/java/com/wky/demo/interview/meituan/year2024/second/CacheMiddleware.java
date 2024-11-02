package com.wky.demo.interview.meituan.year2024.second;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class CacheMiddleware<K, V> {

    private Map<K, V> cacheMap;
    private Map<K, Future<V>> futureMap;
    private ExecutorService executorService;
    private AService aService;
    private long expirationTime; // 缓存过期时间，单位：毫秒
    private ScheduledExecutorService scheduler;

    public CacheMiddleware(AService aService, long expirationTime) {
        this.cacheMap = new HashMap<>();
        this.futureMap = new HashMap<>();
        this.executorService = Executors.newFixedThreadPool(10);
        this.aService = aService;
        this.expirationTime = expirationTime;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public V get(K key) {
        V value = cacheMap.get(key);
        if (value == null) {
            Future<V> future = futureMap.get(key);
            if (future == null) {
                future = executorService.submit(() -> aService.get(key));
                futureMap.put(key, future);
            }
            try {
                value = future.get();
                cacheMap.put(key, value);
                scheduleExpiration(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public void put(K key, V value) {
        cacheMap.put(key, value);
        scheduleExpiration(key);
    }

    public void remove(K key) {
        cacheMap.remove(key);
    }

    public void update(K key) {
        remove(key);
        get(key);
    }

    private void scheduleExpiration(K key) {
        scheduler.schedule(() -> remove(key), expirationTime, TimeUnit.MILLISECONDS);
    }

}
