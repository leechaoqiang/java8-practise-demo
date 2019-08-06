package com.vincent.demo.list;

import java.util.Arrays;
import java.util.List;

/**
 * @author vincent.li3
 * @since 2019/8/5 9:12 PM
 * asList方法接收一个可变参数，并且这个可变参数类型是作为泛型的参数。
 * 那么在Java中基本数据类型不能作为泛型的参数，数组是引用数据类型所以数组是可以泛型化，
 * 所以使用了int[]作为参数类型，而不是用int作为参数类型上面的方法如果补全的话应该是以下。
 *
 *
 * [F, K, B, C, D, A],size:6
 * [[I@49476842,size:1
 * [[I@78308db1,size:5
 * */
public class ArraysTestClient {

    public static void main(String[] args){
        String[] strData = {"F","K","B","C","D","A"};
        List<String> stringList = Arrays.asList(strData);
        System.out.println(stringList.toString()+",size:"+stringList.size());


        int[] intData = {3,2,9,11,1};
        List<int[]> intList = Arrays.asList(intData);
        System.out.println(intList.toArray().toString()+",size:"+intList.size());

        Integer[] integerData = {1,2,9,11,5};
        List<Integer> integerList = Arrays.asList(integerData);
        System.out.println(intList.toArray().toString()+",size:"+integerList.size());

    }
}
