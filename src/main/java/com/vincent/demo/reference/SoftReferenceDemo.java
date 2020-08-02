package com.vincent.demo.reference;


import java.lang.ref.SoftReference;

/**
 * @author vincent.li
 * @Description 软引用关联的对象，在内存不够的情况下，会把这些软引用关联的对象列入垃圾回收范围中，然后进行回收，也就是说软引用并非是完全安全的，在内存不够的情况下是会被垃圾回收器回收掉的。
 * @since 2020/6/28
 */
public class SoftReferenceDemo {


    public static void main(String[] args) {
        int count = 5;
        SoftReference[] softReference = new SoftReference[5];
        //初始化对象数组
        for (int i = 0; i < count; i++) {
            softReference[i] = new SoftReference<>(new BigObject("BigObject-"+i));
        }
        //遍历打印数组
        for (int i = 0; i < count; i++) {
            Object o = softReference[i].get();
            if (null == o) {
                System.out.println("this object is null");
            } else {
                System.out.println(((BigObject)o).getName());
            }
        }

    }

}
