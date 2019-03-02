package com.dengchong.algorithms.question_1;

import java.io.Serializable;

public class Singleton {
}

//1.懒汉模式,线程不安全
class Singleton1 {
    private static Singleton1 instance;

    private Singleton1() {

    }

    public static final Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}

//2.懒汉模式,线程安全
//synchronized修饰整个方法,效率低
class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {

    }

    public static synchronized final Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

//3.懒汉模式,双检查锁
//synchronized代码块,jdk1.5后,双检查锁才能正常达到单列效果
class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {

    }

    public static final Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}

//4.饿汉模式
//基于classloader机制避免了多线程同步问题,但instance在类装载就实例化了.getInstance不是导致类装载的唯一方法,导致没有达到lazy loading效果
class Singleton4 {
    private static final Singleton4 instance = new Singleton4();

    private Singleton4() {

    }

    public static final Singleton4 getInstance() {
        return instance;
    }
}

//5.静态内部类
//同样基于classloader机制达到同步效果,但将instance实例化放在了静态内部类加载时,达到了lazy loading效果
class Singleton5 {
    private static class Singleton5Holder {
        private static final Singleton5 instance = new Singleton5();
    }

    private Singleton5() {

    }

    public static final Singleton5 getInstance() {
        return Singleton5Holder.instance;
    }
}

//6.枚举
//Effective Java作者提倡的方法,不仅能避免多线程同步问题,还能防止反序列化重新创建新对象.jdk1.5加入enum
enum Singleton6 {
    INSTANCE,
}

//问题:如果Singleton实现了Serializable接口,那么反序列号会生成多个单例类实例
//解决方法
class Singleton7 implements Serializable {
    public static Singleton7 INSTANCE = new Singleton7();

    private Singleton7() {

    }

    public static final Singleton7 getInstance() {
        return INSTANCE;
    }

    //该方法保证反序列化不生成新对象
    private Object readResolve() {
        return INSTANCE;
    }
}