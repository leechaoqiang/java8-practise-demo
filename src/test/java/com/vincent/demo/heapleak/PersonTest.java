package com.vincent.demo.heapleak;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PersonTest {

    @Test
    public void givenMap_whenEqualsAndHashCodeNotOverridden_thenMemoryLeak() {
        Map<Person2, Integer> map = new HashMap<>();

        System.out.println("debug point 1");
        for (int i = 0; i < 1000; i++) {
            map.put(new Person2("joe"), 1);
        }
        Assert.assertTrue(map.size() == 1);

    }
    @Test
    public void givenMap_whenEqualsAndHashCodeOverridden_thenMemoryLeak() {
        Map<Person, Integer> map = new HashMap<>();
        System.out.println("debug point 1");
        for (int i = 0; i < 2; i++) {
            map.put(new Person("joe"), 1);
        }
        Assert.assertTrue(map.size() == 1);
    }

}
