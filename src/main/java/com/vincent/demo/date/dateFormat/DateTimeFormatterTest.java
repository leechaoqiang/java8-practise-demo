package com.vincent.demo.date.dateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterTest {
    public static void main(String[] args){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2020-03-12", formatter);
        System.out.println(formatter.format(date));
    }
}
