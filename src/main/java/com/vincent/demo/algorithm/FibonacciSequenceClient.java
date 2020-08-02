package com.vincent.demo.algorithm;

public class FibonacciSequenceClient {

    public static int fib(int n){
        if( n < 1)
            return -1;
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n-1) + fib(n - 2);
    }

    public static int fib1(int n){
        if( n < 1)
            return -1;
        if (n == 1 || n == 2) {
            return 1;
        }
        int res = 1, pre = 1,temp = 0;

        for (int i = 3; i <= n; i++) {
            temp = res;
            res = pre + res;
            pre = temp;
        }
        return res;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("斐波那契数列r：" + (fib( 10)));
        long end = System.currentTimeMillis();

        long start1 = System.currentTimeMillis();
        System.out.println("斐波那契数列s1：" + (fib1( 10)));
        long end1 = System.currentTimeMillis();

        System.out.println("fib:time:cost:"+(end-start));
        System.out.println("fib1:time:cost:"+(end1-start1));
    }
}
