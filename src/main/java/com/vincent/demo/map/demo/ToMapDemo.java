package com.vincent.demo.map.demo;

/**
 * @author vincent.li
 * @Description ToMap测试demo
 * @since 2020/6/23
 */
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class ToMapDemo {
    public static void main(String[] args) {
        List<String> duplicateList = Arrays.asList("a", "bb", "c", "d", "bb");

        Map<String, String> map = duplicateList
                .stream()
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                s -> {
                                    return s.length() + " " + (new Random()).nextInt(10);
                                },
                                (preItem, nextItem) -> {
                                    System.out.println("item" + preItem + " curItem: " + nextItem);
                                    return nextItem;
                                }
                        )
                );

        System.out.println(map);

        List<String> givenList = Arrays.asList("a", "bb", "dd", "ccc");
        Map<Integer, Set<String>> groupList = givenList
                .stream()
                .collect(Collectors.groupingBy(
                        String::length,
                        toSet()
                ));

        System.out.println(groupList);
    }
}
