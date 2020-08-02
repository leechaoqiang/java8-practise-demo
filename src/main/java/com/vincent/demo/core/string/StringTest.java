package com.vincent.demo.core.string;


public class StringTest {



    //去除字符串的尾部空字符
    public static String  myTrim(String target){
        if (null == target) {
            return null;
        }
        char[] chars = target.toCharArray();
        StringBuffer buffer = new StringBuffer();
        for (char c : chars) {
            Character ch = c;
            if (0 == ch.hashCode()) {
                break;
            } else {
                buffer.append(c);
            }
        }
        return buffer.toString();

    }

    public static void main(String[] args) {
//        System.out.println("========begin======>>>>>");
//        String testStr = "天地不仁以万物为刍狗 ";
//        System.out.println("testStr====>>>>>" + testStr + "\nmyTrim():exeResult:" + myTrim(testStr));
//        String testStr2 = " 天地不仁以万物为刍狗2 ";
//        System.out.println("testStr2====>>>>>" + testStr2 + "\ntrim():exeResult:" + testStr2.trim());
//        System.out.println("========end======>>>>>");


        System.out.println(String.format("%02d-%02d", 6, 1));
    }
}
