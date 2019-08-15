package com.vincent.demo.folkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @Desc 使用ForkJoinPool完成一个任务的分段执行
 * 简单的打印0-500的数值。用多线程实现并行执行
 * @author   vincent.li
 * @version   1.0
 * @since    JDK 1.7
 * @see
 */
public class ForkJoinPoolAction {

    public static void main(String[] args) throws Exception {
        PrintTask task = new PrintTask(0, 500);
        //创建实例，并执行分割任务
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(task);
        //线程阻塞，等待所有任务完成
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        forkJoinPool.shutdown();
    }
}

/**
 * @Desc: 继承RecursiveAction来实现“可分解”的任务。
 *
 * @author vincent.li
 * @version 1.0
 * @since JDK 1.7
 */
class PrintTask extends RecursiveAction {
    //最多只能打印50个数
    private static final int THRESHOLD = 50;
    private int start;
    private int end;



    public PrintTask(int start, int end) {
        super();
        this.start = start;
        this.end = end;
    }



    @Override
    protected void compute() {

        if(end - start < THRESHOLD){
            for(int i = start; i < end; i++){
                System.out.println(Thread.currentThread().getName()+"打印i值>>>"+i);
            }
        }else {
            int middle = (start + end) / 2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            //并行执行两个“小任务”
            left.fork();
            right.fork();
        }

    }

}
