package com.vincent.demo.map.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {

    public static void main(String[] args){
        Map<String, String> map = new HashMap<>(2);
        map.put("name1", "Obama");
        map.put("name2", "Trump");
        map.put("name3", "Hilton");
        System.out.println(map+",size:"+map.size());
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
    }
}
