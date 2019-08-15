package com.vincent.demo.folkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Desc 对一个长度为10000000的元素值进行累加
 * @author   vincent.li
 * @version   1.0
 * @since    JDK 1.7
 * @see
 */
public class ForJoinPollTask {

    public static void main(String[] args) throws Exception {
        long[] arr = new long[100000000];
        Random random = new Random();
        long total =0L;
        int len = arr.length;
        //初始化10000000个数组元素
        for(int i=0; i<len; i++){
            int temp = random.nextInt(50);
            arr[i]=Long.valueOf(temp).longValue() ;
        }
        System.out.println("初始化数组完成");
        long start = System.currentTimeMillis();
        for(int i=0; i<len; i++){
            //对数组元素赋值，并将数组元素的值添加到sum总和中
            total += arr[i];
        }
        long timeCost = System.currentTimeMillis() - start;
        System.out.println("初始化数组总和：" + total + ",耗时：" + timeCost + " ms");
        start = System.currentTimeMillis();
        SumTask task = new SumTask(arr, 0, len);
//        创建一个通用池，这个是jdk1.8提供的功能
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Future<Long> future = pool.submit(task); //提交分解的SumTask 任务
        timeCost = System.currentTimeMillis() - start;
        System.out.println("多线程执行结果："+future.get()+ ",耗时：" + timeCost + " ms");
        pool.shutdown(); //关闭线程池
    }

}

/**
 * @Desc: 继承抽象类RecursiveTask，通过返回的结果，来实现数组的多线程分段累累加
 *  RecursiveTask 具有返回值
 *
 * @author vincent.li
 * @version 1.0
 * @since JDK 1.7
 */
class SumTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 50; //每个小任务 最多只累加50个数
    private long arry[];
    private int start;
    private int end;



    /**
     * Creates a new instance of SumTask.
     * 累加从start到end的arry数组
     * @param arry
     * @param start
     * @param end
     */
    public SumTask(long[] arry, int start, int end) {
        super();
        this.arry = arry;
        this.start = start;
        this.end = end;
    }



    @Override
    protected Long compute() {
        long sum = 0L;
        //当end与start之间的差小于threshold时，开始进行实际的累加
        if(end - start < THRESHOLD){
            for(int i = start; i < end; i++){
                sum += arry[i];
            }
            return sum;
        } else {//当end与start之间的差大于threshold，即要累加的数超过20个时候，将大任务分解成小任务
            int middle = (start+ end)/2;
            SumTask left = new SumTask(arry, start, middle);
            SumTask right = new SumTask(arry, middle, end);
            //并行执行两个 小任务
            left.fork();
            right.fork();
            //把两个小任务累加的结果合并起来
            return left.join()+right.join();
        }

    }

}
