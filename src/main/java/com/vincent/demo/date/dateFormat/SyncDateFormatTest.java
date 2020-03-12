package com.vincent.demo.date.dateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * 加一把线程同步锁：synchronized(lock)
 * */
public class SyncDateFormatTest {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
    private static String date[] = { "01-Mar-2019", "01-Jan-2020", "01-Jan-2021" };

    public static void main(String[] args) {
        for (int i = 0; i < date.length; i++) {
            final int temp = i;
            new Thread(() -> {

                    try {
                        while (true) {
                            synchronized (sdf) {
                                String str1 = date[temp];
                                Date date = sdf.parse(str1);
                                String str2 = sdf.format(date);
                                System.out.println(Thread.currentThread().getName() + ", " + str1 + "," + str2);
                                if(!str1.equals(str2)){
                                    throw new RuntimeException(Thread.currentThread().getName()
                                            + ", Expected " + str1 + " but got " + str2);
                                }
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("parse failed", e);
                    }
            }).start();
        }
    }
}
