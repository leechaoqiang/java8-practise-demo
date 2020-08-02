package com.vincent.demo.test;


import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class Test {

    public static void main(String[] args){
        int a = 10;
        int b = 10;
//        methoda(a, b);
        System.out.println("a-->"+a);
        System.out.println("b-->"+b);
//        test1();
        test2();

        method1();
        AtomicLong atomicLong = new AtomicLong();
        LongAdder  longAdder = new LongAdder();


    }

    public static void method1() {
        System.out.println(Integer.MAX_VALUE);

        Integer i = new Integer(100);
        int j = 100;
        System.out.println(i == j); //true

        Integer i1 = new Integer(100);
        Integer j1 = 100;
        System.out.println(i1 == j1); //false

        Integer i2 = 100;
        Integer j2 = 100;
        System.out.println(i2 == j2); //true

        Integer i3 = 128;
        Integer j3 = 128;
        System.out.println(i3 == j3); //false
    }

    public static void methoda(int a,int b) {
        System.out.println("a-->"+100);
        System.out.println("b-->"+200);
        System.exit(0);
    }

    public static void test1(){
        double d = 0.1d;
        for (int i= 0 ; i < 30;i++){
            System.out.println((i+1)+":"+ (d = 2*i*d));
        }
    }

    public static void test2() {
        String str = "提示:#content补价日期2018-10-16至2018-10-16 每晚底价107.10 卖价119.00 不含早 #br由于部分预订日期房型价格未知，携程根据历史价格进行智能补价，如果房型价格高于预测价格请及时操作EBK更改价格或联系业务操作。\n" +
                "房费携程支付，无返佣(月结)。房价保密、请不要向客人透露。预付:预付订单，客人已支付房费，请贵酒店务必保留房间\n" +
                "活动信息【天天特价9.5折,19.24;天天特价9.5折,19.24;天天特价9.5折,19.24】\n" +
                "哈哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈三一";

        System.out.println(str.substring(0, 255));
    }
    public static void formatTest() {
        //补0
        System.out.println(String.format("%05d", 12));
        String str=null;
        //$使用
        str=String.format("格式参数$的使用：%1$d,%2$s", 99,"abc");
        System.out.println(str);
        //+使用
        System.out.printf("显示正负数的符号：%+d与%d%n", 99,-99);
        //补O使用
        System.out.printf("最牛的编号是：%03d%n", 7);
        //空格使用
        System.out.printf("Tab键的效果是：% 8d%n", 7);
        //.使用
        System.out.printf("整数分组的效果是：%,d%n", 9989997);
        //空格和小数点后面个数
        System.out.printf("一本书的价格是：% 50.5f元%n", 49.8);
        //空格和小数点后面个数
        System.out.printf("一本书的价格1是：%.2f元%n", 49.8);


    }
}
