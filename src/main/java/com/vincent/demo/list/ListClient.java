package com.vincent.demo.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListClient {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n=100;
        List<Object> list = new ArrayList();
        for(int i = 0 ;i < n; i++) {

            list.add(new Random().nextInt(20));
            System.out.println(list.get(i));
        }
        System.out.println(list.subList(0, 29));
        System.out.println(list.size());
        System.out.println(list.subList(30, 59));
        System.out.println(list.subList(60, 89));
        System.out.println(list.subList(90, 99));

        int[] endpoints = {0, (list.size() + 1)/2, list.size()};
        List<List<Object>> lists =
        IntStream.rangeClosed(0, 1)
                .mapToObj(i -> list.subList(endpoints[i], endpoints[i + 1]))
                .collect(Collectors.toList());



    }

}
