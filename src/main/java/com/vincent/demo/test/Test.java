package com.vincent.demo.test;


public class Test {

    public static void main(String[] args){
        int a = 10;
        int b = 10;
//        methoda(a, b);
        System.out.println("a-->"+a);
        System.out.println("b-->"+b);
//        test1();
        test2();
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

    public static void test2(){
       String str = "提示:#content补价日期2018-10-16至2018-10-16 每晚底价107.10 卖价119.00 不含早 #br由于部分预订日期房型价格未知，携程根据历史价格进行智能补价，如果房型价格高于预测价格请及时操作EBK更改价格或联系业务操作。\n" +
               "房费携程支付，无返佣(月结)。房价保密、请不要向客人透露。预付:预付订单，客人已支付房费，请贵酒店务必保留房间\n" +
               "活动信息【天天特价9.5折,19.24;天天特价9.5折,19.24;天天特价9.5折,19.24】\n" +
               "哈哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈三一";

            System.out.println(str.substring(0, 255));

    }
}
