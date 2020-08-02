package com.vincent.demo.interview;

import java.util.Arrays;
import java.util.Collections;

public class Test {

    public static void main(String[] args){
        int[] ary = {};

    }

    /**
     * 查找一个数字，数字是非排序好的素组，单调递增或者单调递减，旋转过的
     * 1 2 3 4 5 6
     * 3 4 5 6 1 2
     * */
    public int test1(int ary[], int target){
        if (null == ary || ary.length == 0) {
            return -1;
        }

        // 1.排序

//        Arrays.sort(ary, 0, ary.length -1);

        // 2.查找target的index
//        for (int i =0; i < ary.length; i++) {
//            if (target == ary[i]) {
//                return i;
//            }
//        }




        return -1;
    }

    /**
     *
     * 3
     * 3
     * beginIndex 0, 0
     *
     * 3,5
     * 4
     * 0,1
     *
     *
     * */
    public int binarySelect(int ary[], int target, int beginIndex, int endIndex){

        if (target < ary[beginIndex] || ary[endIndex] < target) {
            return -1;
        }

        int middleIndex  = beginIndex + (endIndex - beginIndex)/2;
        if (ary[middleIndex] == target) {
            return middleIndex;
        } else if (ary[middleIndex] < target) {
           return binarySelect(ary, target, beginIndex, middleIndex);
        } else {
            return binarySelect(ary, target, middleIndex, endIndex);
        }
    }
    /**
     *
     * 3
     * 3
     * beginIndex 0, 0
     *
     * 3,5
     * 4
     * 0,1
     *
     *
     * */
    public int binarySelect2(int ary[], int target){

        int beginIndex = 0; //第一个下标
        int endIndex = ary.length - 1;//最后一个下标

        if (target < ary[beginIndex] || target > ary[endIndex] || beginIndex > endIndex) {
            return -1;
        }
        int middle = 0;
        while (beginIndex <= endIndex) {
            middle = (beginIndex + endIndex) / 2;
            if (ary[middle] == target) {
                return middle;
            } else if (ary[middle] < target) {
                beginIndex = middle + 1;
            } else {
                endIndex = middle - 1;
            }
        }
        return -1;
    }


}
