package com.wky.demo.juc;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 这里并不是简单的合并请求
 *
 * @author wuming
 * @date 2023/8/5/08/05 10:40
 */
@Slf4j
@Service
public class CompletableFutureService {

    private final LinkedBlockingQueue<Request> requestsd = new LinkedBlockingQueue<>();

    private final Map<String, CompletableFuture<Boolean>> requests = new ConcurrentHashMap<>();

    /**
     * 对象初始化完成后执行
     */
    @PostConstruct
    private void init() {
        // 开启一个定时线程
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        // 每隔10ms执行一次
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Set<String> nos = requests.keySet();
            if (CollectionUtils.isEmpty(nos)) {
                // 没有请求
                return;
            }
            log.info("开始处理本批次请求：{}", JSONUtil.toJsonStr(nos));
            // 批量查询支付结果
            Map<String, Boolean> results = batchGetResult(nos);
            for (Map.Entry<String, Boolean> entry : results.entrySet()) {
                String key = entry.getKey();
                CompletableFuture<Boolean> future = requests.get(key);
                if (future != null) {
                    future.complete(entry.getValue());
                }
                requests.remove(key);
            }

        }, 0, 10, TimeUnit.MILLISECONDS);
    }

    private Map<String, Boolean> batchGetResult(Set<String> nos) {
        Map<String, Boolean> results = new HashMap<>();
        for (String no : nos) {
            results.put(no, true);
        }
        return results;
    }


    /**
     * pos机支付接口
     * 需要同步返回支付结果
     * 但是第三方支付接口是异步的
     * 因此我们需要对请求线程进行阻塞
     * 然后再异步批量查询支付结果
     */
    public boolean pay() throws ExecutionException, InterruptedException {
        // 调用第三方支付接口获取单号
        String number = RandomStringUtils.randomAlphabetic(3);
        // 为了防止服务器宕机，支付单号丢失，需要打印到日志文件中，应该是会有支付记录的
        log.info("pay number：{}", number);
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        requests.put(number, future);
        return future.get();
    }

    BlockingQueue<Request> queue = new LinkedBlockingQueue<>();


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
    }

}
