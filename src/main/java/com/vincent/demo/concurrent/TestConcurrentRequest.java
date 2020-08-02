package com.vincent.demo.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.vincent.demo.utils.HttpClientUtil;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 目标：编写一段代码，模拟并发请求为50，且总的请求数为5000，当5000个请求完成后，打印“请求完成”
 */
public class TestConcurrentRequest {

    // 总的请求个数
    public static final int requestTotal = 5000;

    // 同一时刻最大的并发线程的个数
    public static final int concurrentThreadNum = 50;

    public static ExecutorService executorService0 = Executors.newSingleThreadExecutor();
    public static ExecutorService executorService1 = Executors.newCachedThreadPool();
    public static ExecutorService executorService2 = Executors.newFixedThreadPool(10);
    AtomicReference atomicReference = new AtomicReference();


    public static void main(String[] args) throws InterruptedException {

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("concurrent-pool-%d").build();
        ExecutorService executorService =
        new ThreadPoolExecutor(concurrentThreadNum, concurrentThreadNum,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(requestTotal),
                threadFactory);
        CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        Semaphore semaphore = new Semaphore(concurrentThreadNum);
        for (int i = 0; i< requestTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    String result = testRequestUri();
                    System.out.println(Thread.currentThread().getName()+":" + ",result: " + result);
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.err.println("exception:" + e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("请求完成");
    }

    private static String testRequestUri() {
        return "pong";
//        return HttpClientUtil.doGet("http://localhost:5000/ping");
    }
}