package com.demo.springbootdemo.study.designPattern;

import com.demo.springbootdemo.study.Demo;

/**
 * 单例模式
 *
 * @author: guan.kai
 * @date: 2019/12/16 14:51
 **/
public class Singleton {

    /*
    // 饿汉模式（线程安全）
    private static Demo demo = new Demo();
    private Singleton() {
    }
    public static Demo getDemo() {
     return demo;
    }
    */

    /*
    // 懒汉模式（线程不安全）
    private static Demo demo;
    private Singleton() {
    }
    public static Demo getDemo() {
        if (demo == null) {
            demo = new Demo();
        }
        return demo;
    }
    */

    /*
    // 懒汉模式（线程安全 但 消耗资源）
    private static Demo demo;
    private Singleton() {
    }
    public static synchronized Demo getDemo() {
        if (demo == null) {
            demo = new Demo();
        }
        return demo;
    }
    */

    /*
    // 懒汉模式（线程安全，双层检验，提升效率）
    private static volatile Demo demo;
    private Singleton() {
    }
    public static Demo getDemo() {
        if (demo == null) {
            synchronized (Singleton.class) {
                if (demo == null) {
                    demo = new Demo();
                }
            }
        }
        return demo;
    }
    */

    // 懒汉模式（线程安全，使用静态内部类实现）
    private static class ThisDemo {
        private static final Demo DEMO = new Demo();
    }
    private Singleton() {
    }
    public static final Demo getDemo() {
        return ThisDemo.DEMO;
    }


}


// 枚举类单例
enum ThisDemo {
    DEMO;

    private final Demo demo;
    ThisDemo() {
        demo = new Demo();
    }

    public Demo getDemo() {
        return demo;
    }

}

class Test {
    public static void main(String[] args) {
        ThisDemo.DEMO.getDemo();
    }
}