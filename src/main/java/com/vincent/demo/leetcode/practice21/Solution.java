package com.vincent.demo.leetcode.practice21;

/***
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 */
public class Solution {
    public static void main(String[] args){
        Solution solution = new Solution();
        ListNode l1 = null;
        ListNode current = null;    //对下一个节点的引用


        for (int i = 1; i < 5; i ++) {
            if (null == l1) {

                l1 =  new ListNode(i);
                current = l1;

            } else {
                current.next =   new ListNode(i);
                current = current.next;

            }

        }
        ListNode l2 = null;
        ListNode current2 = null;    //对下一个节点的引用
        for (int i = 2; i < 6; i ++) {
            if (null == l2) {

                l2 =  new ListNode(i);

                current2 = l2;
            } else {

                current2.next = new ListNode(i);
                current2 = current2.next;
            }

        }
        System.out.println("\n l1: ");
        solution.print(l1);
        System.out.println("\n l2: ");
        solution.print(l2);

        ListNode newListNode =   solution.mergeTwoLists(l1, l2);
        System.out.println("\n newListNode: ");
        solution.print(newListNode);


    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {


        ListNode newNode = new ListNode(0);
        ListNode current = newNode;
        while(null != l1 && null != l2){
            if (l1.val < l2.val) {
                current.next = l1;
                current = current.next;
                l1 = l1.next;

            } else {
                current.next = l2;
                current = current.next;
                l2 = l2.next;

            }
        }
        if (l1 == null) {
            current.next = l2;
        } else {
            current.next = l1;
        }
        return newNode.next;
    }

    public void print(ListNode node){
        if (null == node) {
            return;
        }
        while (node != null) {
            System.out.print(node.val+" ");
            node = node.next;
        }
    }


}
