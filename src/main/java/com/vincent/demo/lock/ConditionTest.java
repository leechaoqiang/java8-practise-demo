package com.vincent.demo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition接口提供了类似Object的wait、notify和notifyAll方法，与Lock配合可以实现生产/消费模式，但是这两者在使用方式以及功能特性上还是有差别的。
 *
 * */
public class ConditionTest {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    // 初始数据
    private static int data = 0;
    // 是否被消费
    private static volatile boolean consumed = false;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                produceData();
            }
        },"producer").start();

        new Thread(() -> {
            while (true) {
                consumeData();
            }
        },"consumer").start();
    }

    public static void produceData() {
        try {
            lock.lock(); // 获取锁
            while (!consumed) { // 判断数据是否被消费
                condition.await(); // 如果没有被消费则进入等待
            }
            TimeUnit.SECONDS.sleep(1);
            data++;
            System.out.println(Thread.currentThread().getName() + " produce data = " + data);
            consumed = false; // 生产完数据将消费标识置为false
            condition.signal(); // 解除await，用于通知消费者可以开始消费了
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // 释放锁
        }
    }

    public static void consumeData() {
        try {
            lock.lock(); // 获取锁
            while (consumed) { // 判断数据是否被消费
                condition.await(); // 如果被消费了则进入等待
            }
            TimeUnit.MICROSECONDS.sleep(500);
            System.out.println(Thread.currentThread().getName() + " consume data = " + data);
            consumed = true; // 消费完将消费标识置为true
            condition.signal(); // 解除await，有领域通知生产者可以开始生产了
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // 释放锁
        }
    }
}
