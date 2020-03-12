package com.vincent.demo.classloader;

public class ClassLoadTest1 {
    private static ClassLoadTest1 test = new ClassLoadTest1(); 
    static int x; 
    static int y = 0;
    public ClassLoadTest1() { 
      x++; 
      y++;
    }
    public static void main(String[] args) {
        System.out.println(test.x);
        System.out.println(test.y);
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
