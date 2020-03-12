package com.vincent.demo.date.dateFormat;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 验证 SimpleDateFormat 是线程不安全的
 */
public class DateFormatClient {
    /**
     * 只创建一份，避免频繁地创建对象和销毁对象
     * 单线程下可以不出错
     * 多线程下则不安全, 不同的线程会对不同日期字符串进行解析，
     * 会出现线程 A 解析到一半被挂起，线程 B 运行时将 A 的解析到一半的字符串覆盖掉，
     * 这样轮到 A 运行时会解析失败，或者使用了 B 的字符串
     */
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 线程安全的 DateTimeFormatter
     * 推荐使用，因为该类是不可变的，并且是线程安全的
     */
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void formatTest() {
        ExecutorService service = Executors.newFixedThreadPool(10);

        for(int i = 0; i < 10; i++){
            service.execute(new Thread(() -> {
                    String dateStr = "2020-03-11 10:26:30";
                    // 解决方法
                    // 1、可以只在需要时创建对象，也可以避免错误，但是频繁创建与销毁会导致额外的开销，性能低
//                    SimpleDateFormat newInNeed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    for(int j = 0; j < 10; j++){
//
//                        try {
                        // 直接使用不但运行结果错误，最后还会抛出 NumberFormatException: 异常
//                            System.out.println(format.parse(dateStr));

                        // 2、使用加了 synchronized 的同步方法，但是并发量高时，性能影响大，线程阻塞
//                            System.out.println(ConcurrentDateFormat.parse(dateStr));

                        // 3、使用 ThreadLocal 来解决，比较优雅的一种做法
//                            System.out.println(ThreadLocalDateFormat.parse(dateStr));

//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
                        // 4、使用DateTimeFormatter，该类是线程安全的，可以放心使用
                        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, dtf);
                        System.out.println(localDateTime);
                    }
                 })
            );
        }
    }
}
