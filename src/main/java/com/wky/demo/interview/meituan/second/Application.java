package com.wky.demo.interview.meituan.second;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 编写一个Java函数，通过调用AService.get()、BService.get()、CService.get()三个接口，获取三个整数，然后将这三个整数累加，最终返回累加的值。
 * 要求：
 * 1.调用三个接口的操作需要并行执行，以提高效率；
 * 2.累加操作需要在获取三个整数的操作完成后进行，因此需要保证三个整数均已获取后才能进行累加操作；
 * 3.考虑多线程安全问题。
 * @author wuming
 * @date 2023/8/3/08/03 15:46
 */
public class Application {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        AService aService = new AService();
        BService bService = new BService();
        CService cService = new CService();

        CompletableFuture<Integer> a = CompletableFuture.supplyAsync(aService::get);
        CompletableFuture<Integer> b = CompletableFuture.supplyAsync(bService::get);
        CompletableFuture<Integer> c = CompletableFuture.supplyAsync(cService::get);

        // 等待上面三个任务，都完成后对返回结果进行累加操作
        CompletableFuture<Void> allOf = CompletableFuture.allOf(a, b, c);
        allOf.get();
        System.out.println(a.get() + b.get() + c.get());
    }

}
