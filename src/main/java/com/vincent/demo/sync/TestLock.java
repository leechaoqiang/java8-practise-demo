package com.vincent.demo.sync;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xq on 17/6/26.
 */
public class TestLock {
    private ArrayList<String> arrayList = new ArrayList<>();
    Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        final TestLock test = new TestLock();
        for (int i = 0; i < 3; i++) {

            final Integer count=i;
            new Thread("甲"){public void run() {
                test.insert(Thread.currentThread(),count);
            };}.start();
            new Thread("乙"){public void run() {
                test.insert(Thread.currentThread(),count);
            };}.start();

        }
        test.arrayList.stream().forEach(e->{
            System.out.println(e);
        });
    }

    public void insert(Thread thread,Integer count) {

        lock.lock();
        try {
            //线程获取到了锁
            for (int i = 0; i<5; i++) {
                arrayList.add("第"+count+"次"+"线程"+thread.getName()+i);
            }
        } catch (Exception e) {

        }finally {
            //线程释放锁
            lock.unlock();
        }
    }
}