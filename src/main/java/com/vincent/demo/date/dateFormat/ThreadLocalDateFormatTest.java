package com.vincent.demo.date.dateFormat;

import com.vincent.demo.date.DateUtil;

import java.util.Date;

public class ThreadLocalDateFormatTest {
    private static String date[] = { "01-Jan-2019", "01-Feb-2020", "01-Mar-2021" };

    public static void main(String[] args) {
        for (int i = 0; i < date.length; i++) {
            final int temp = i;
            new Thread(() -> {
                    try {
                        while (true) {
                            String str1 = date[temp];
                            Date date = DateUtil.parse(str1);
                            String str2 = DateUtil.format(date);
                            System.out.println(Thread.currentThread().getName()
                                    + "," + str1 + "," + str2);
                            if(!str1.equals(str2)){
                                throw new RuntimeException(Thread.currentThread().getName()
                                        + ", Expected " + str1 + " but got " + str2);
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("parse failed", e);
                    }
            }).start();
        }
    }
}
