package com.vincent.demo.date.dateFormat;

import java.text.SimpleDateFormat;
import java.util.Locale;
/***
 * SimpleDateFormat是线程不安全的。</br>
 * 如果我们把SimpleDateFormat定义成static成员变量，
 * 那么多个thread之间会共享这个SimpleDateFormat对象，
 * 所以Calendar对象也会共享。
 *
 */
public class DateFormatTest {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
    private static String date[] = { "01-Jan-2019", "09-Jan-2020", "08-Jan-2001" , "07-Jan-2002" , "06-Jan-2003" , "05-Jan-2004" , "04-Feb-2005" , "03-Jan-2006" , "02-Jan-2009" };

    public static void main(String[] args) {
        for (int i = 0; i < date.length; i++) {
            final int temp = i;
            new Thread(() ->  {
                    try {
                        while (true) {
                            String str1 = date[temp];
                            String str2 = sdf.format(sdf.parse(str1));
                            System.out.println(Thread.currentThread().getName() + ", " + str1 + "," + str2);
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
