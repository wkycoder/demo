package com.wky.demo.interview.meituan.year2024.first;


import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Service {

    private final UserService userService;

    public Service(UserService userService) {
        this.userService = userService;
    }

    public Map<Long, String> get(List<Long> userIds) throws InterruptedException {
        Map<Long, String> resultMap = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // 对用户ID分批处理，每批次最多50个
        int batchSize = 50;
        int totalCount = (int) Math.ceil((double) userIds.size() / batchSize);
        CountDownLatch countDownLatch = new CountDownLatch(totalCount);
        for (int i = 0; i < userIds.size(); i += batchSize) {
            int end = Math.min(i + batchSize, userIds.size());
            List<Long> batchUserIds = userIds.subList(i, end);
            executor.execute(() -> {
                try {
                    Map<Long, String> userMap = userService.getUserMap(batchUserIds);
                    resultMap.putAll(userMap);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        if (!countDownLatch.await(200, TimeUnit.MILLISECONDS)) {
            throw new RuntimeException("执行时间超过200ms");
        }
        executor.shutdown();
        return resultMap;
    }


}