package com.vincent.demo.date;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * @author vincent.li3
 * @since 2019/8/14 4:31 PM
 */
public class DateClient {

    public static void main(String[] args) {
        Date start = new Date();
        start.setHours(13);
        Date end = new Date();
        System.out.println(start.getTime() +", end:"+end.getTime()+", between:"+(end.getTime()-start.getTime()));
        DecimalFormat df = new DecimalFormat("0.00");
        BigDecimal processTimeCost = new  BigDecimal((end.getTime() - start.getTime())).divide(new BigDecimal(3600000L),2, BigDecimal.ROUND_HALF_UP) ;

        System.out.println(start.getTime() +", end:"+end.getTime()+", between:" + processTimeCost);

    }
}
