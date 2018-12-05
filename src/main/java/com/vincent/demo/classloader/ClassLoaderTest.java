package com.vincent.demo.classloader;



public class ClassLoaderTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ClassLoader cl = ClassLoaderTest.class.getClassLoader();

        System.out.println("ClassLoader is:"+cl.toString());

//        cl = int.class.getClassLoader();

        System.out.println("ClassLoader Parent is:"+cl.getParent().toString());

        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(Thread.currentThread().getContextClassLoader().toString());

        sun.misc.Launcher l = sun.misc.Launcher.getLauncher();
        System.out.println("sun.misc.Launcher ClassLoader is:"+l.getClassLoader().toString());


    }

}
