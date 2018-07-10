package com.jimmy.designpattern.singleton;

/**
 * 首先，我们都知道enum是由class实现的，换言之，enum可以实现很多class的内容，包括可以有member和member function，
 * 这也是我们可以用enum作为一个类来实现单例的基础。另外，由于enum是通过继承了Enum类实现的，enum结构不能够作为子
 * 类继承其他类，但是可以用来实现接口。此外，enum类也不能够被继承，在反编译中，我们会发现该类是final的。
 * <p>
 * 其次，enum有且仅有private的构造器，防止外部的额外构造，这恰好和单例模式吻合，也为保证单例性做了一个铺垫。这里
 * 展开说下这个private构造器，如果我们不去手写构造器，则会有一个默认的空参构造器，我们也可以通过给枚举变量参量来
 * 实现类的初始化。这里举一个例子。
 * <p>
 * 如果用枚举去实现一个单例，这样的加载时间其实有点类似于饿汉模式
 * <p>
 * 因此，选择枚举作为Singleton的实现方式，相对于其他方式尤其是类似的饿汉模式主要有以下优点：
 * <p>
 * 1. 代码简单
 * <p>
 * 2. 自由序列化
 */
public enum Singleton2 implements UserInfoSingleton {


    INSTANCE {
        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }
    };
    String name;

    public static Singleton2 getInstance() {
        return INSTANCE;
    }


}


