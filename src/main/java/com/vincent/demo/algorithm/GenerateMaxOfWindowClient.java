package com.vincent.demo.algorithm;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * 问题描述（等级：尉）
 *
 *
 *
 * 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置。
 *
 * 例如，数组为[4,3,1,5,4,3,7,5]，窗口大小为5时:
 *
 * [4 3 1 5 4] 3 7 5 　max = 5
 *
 * 4 [3 1 5 4 3] 7 5 　max = 5
 *
 * 4 3 [1 5 4 3 7] 5 　max = 7
 *
 * 4 3 1 [5 4 3 7 5]  　max = 7
 *
 * 即窗口最大值数组为 result = {5, 5,7,7}
 * */
public class GenerateMaxOfWindowClient {

    //暴力法求解
    public static int[] getMaxWindow(int[] arr, int w) {
        if (w < 1 || arr == null || arr.length < w) {
            return null;
        }
        int[] result = new int[arr.length - w + 1];
        int index = 0;
        //暴力求解直接从第 w-1个元素开始遍历
        for (int i = w - 1; i < arr.length; i++) {
            int max = arr[i];
            //找出最大值
            for (int k = i; k > i - w; k--) {
                if (max < arr[k]) {
                    max = arr[k];
                }
            }
            result[index++] = max;
        }
        return result;
    }

    //优化
    public static int[] getMaxWindow2(int[] arr, int w) {
        if (w < 1 || arr == null || arr.length < w) {
            return null;
        }
        //用来保存成为最大窗口的元素
        int[] result = new int[arr.length - w + 1];
        int index = 0;
        //用链表从当双向队列。
        LinkedList<Integer> temp = new LinkedList<>();
        //刚才演示的时候，我i直接从i = w-1那里开始演示了。
        for (int i = 0; i < arr.length; i++) {
            //如果队列不为空，并且存放在队尾的元素小于等于当前元素，那么
            //队列的这个元素就可以弹出了，因为他不可能会是窗口最大值。
            //【当前元素】指的是窗口向右移动的时候新加入的元素。
            while (!temp.isEmpty() && arr[temp.peekLast()] <= arr[i]) {
                temp.pollLast();//把队尾元素弹出
            }
            //把【当前元素】的下边加入到队尾
            temp.addLast(i);
            //如果队首的元素不在窗口范围内，则弹出
            if (temp.peekFirst() == i - w) {
                temp.pollFirst();//
            }
            if (i >= w - 1) {
                //由于队首存放的是最大值，所以队首总是对应窗口的最大值元素
                result[index++] = arr[temp.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arrayInt = Ints.toArray(Arrays.asList(4,3,1,5,4,3,7,5));
        int[] results = GenerateMaxOfWindowClient.getMaxWindow(arrayInt, 5);
        System.out.println(Joiner.on(",").join(Arrays.stream(results).iterator()));

    }

}
