package com.vincent.demo.leetcode.practice007;
/**
 *
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 *
 * */

public class Solution {

    public static void main(String[] args){
        int x = 1 << 31;
        System.out.println(x+",reverse result:"+new Solution().reverse(x));
    }

    public int reverse2(int x) {
        String str = String.valueOf(x);
        int flag = 1;
        if (str.startsWith("-")){
            flag = -1;
            str = str.replace("-", "");
        }
        str = new StringBuilder(str).reverse().toString();
        try {
            return Integer.valueOf(str).intValue() * flag;
        } catch (NumberFormatException e) {
            return 0;
        }

    }

    public int reverse(int x) {
        long res = 0;

        while(x != 0){
            int pop = x % 10;
            res = res * 10 + pop;

            if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE){
                return 0;
            }
            x /= 10;
        }
        return (int)res;
    }
}
