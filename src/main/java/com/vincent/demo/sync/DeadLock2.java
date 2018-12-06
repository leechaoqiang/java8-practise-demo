package com.vincent.demo.sync;

public class DeadLock2 {
    public static void main(String[] args) {
        DeadLock2 deadLock2 = new DeadLock2();
        deadLock2.recursive();
    }
    public void recursive(){
        this.businessLogic();
    }
    public  void businessLogic(){
        System.out.println("处理业务逻辑");
        this.saveToDB();
        this.recursive();
    }
    public synchronized void saveToDB(){
        System.out.println("保存到数据库");
    }
}
