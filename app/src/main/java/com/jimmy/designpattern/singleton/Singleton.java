package com.jimmy.designpattern.singleton;

/**
 * 1. 为何要检测两次？
 * A： (在多线程环境下), 有可能延迟加载或者缓存的原因, 造成构造多个实例, 违反了单例的初衷
 * 2. 构造函数能否公开化？
 * A:  No. 单例类的构造函数必须私有化,  单例类不能被实例化,  单例对象只能静态调用
 * 3. lock 锁住的对象为什么要是Object对象, 可以是基本类型吗？
 * A:  No. 锁住的必须是一个引用类型, 不能是值类型, 每个不同的线程在声明的时候值类型变量的地址都不一样,
 * 那么上个线程锁住的东西下个线程进来会认为根本没锁, 相当于每次都锁了不同的门, 没有任何卵用。
 * 而引用类型的变量地址是相同的, 每个线程进来判断锁多想是否被锁的时候都是判断同一个地址, 相当于是锁在通一扇门, 起到了锁的作用。
 * <p>
 * <p>
 * <p>
 * 双检锁单例
 * 同步锁外判断，为避免在实例已经创建的情况下每次获取实例都加锁取，影响性能；
 * 锁内判断，考虑多线程情况下，两个以上线程都已经运行至同步锁处，也就是都已经判断变量为空，如锁内不再次判断，会导致实例重复创建
 * <p>
 * 这是c++的双重检查机制的写法，不过在java这里是无效的。
 * https://www.jianshu.com/p/4586cb11c62d
 * <p>
 * 1、A线程和B线程获取instance，此时如果instance为空，假设A线程先拿到锁，然后判断instance为空，则创建一个对象，接着释放锁；紧接着B拿到锁，此时判断instance为空不成立，直接释放锁并返回，明显少创建了一次对象。Double Check的目的是在临界状态下少创建对象。
 * <p>
 * 2、经过步骤1，instance不为空，外面的那个判断if(instance == null)不成立，直接返回instance，可以减少锁定，提高性能
 * <p>
 * 第一层if是为了减少锁定
 *
 * http://www.infoq.com/cn/articles/double-checked-locking-with-delay-initialization
 */
public class Singleton {

    private Singleton() {
    }                    //1. 私有化构造器

    private static volatile Singleton instance = null; //2. 静态初始化单例对象
    private static Object obj = new Object();

    public static Singleton getInstance() { //3. 通过静态方法来构造对象
        if (instance == null) { //4. 判断单例对象是否已经存在

            //synchronized (obj) {  //5. 加线程锁实现线程安全, 可以使用SingletonDemo.class代替, 避免创建多个对象
            synchronized (Singleton.class) { //这里锁的是一个静态对象, 如果不想创建, 就锁该类自身的Class对象

                if (instance == null) { //6. 再次判断单例对象是否存在

                    instance = new Singleton(); //7. 构造对象
                }
            }
        }
        return instance;
    }
}
