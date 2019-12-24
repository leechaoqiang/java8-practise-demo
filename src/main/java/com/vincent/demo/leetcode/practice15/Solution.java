package com.vincent.demo.leetcode.practice15;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.List;

/***
 *
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 *
 * */
public class Solution {

    public static void main(String[] args){
        int nums[] = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(JSON.toJSONString(new Solution().threeSum(nums)));
    }
    public List<List<Integer>> threeSum(int[] nums) {

        return Lists.newArrayList();
    }
}
