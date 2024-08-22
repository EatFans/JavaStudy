package cn.eatfan;

/**
 * Java 接口
 */
public class Study6 {
    /**
     * 什么是接口？
     * 接口是一个抽象类型，它允许你定义一组方法，但不包含具体实现。接口可以被类实现（使用implements关键字），一旦一个类实现了某个接口，它必须提供接口中所有方法的具体实现。
     * 在Java中，接口是一种契约，规定了类必须具备哪些行为，但不关心这些行为是如何实现的。
     *
     * 注意：
     * 在下面代码中类名出现数字，是因为在其他.java文件中也使用到类似的命名，为了方便和类名不冲突，在这里就使用了数字
     */
    public static void main(String[] args){
        // 创建一个Dog3实例对象，类型为Animal3
        // Animal3为一个接口，没有实际方法实现的代码，无法被实例化
        // Dog3类实现了Animal3接口，实现了多态，在这里就可以用Animal3类型来创建Dog3类的实例对象
        Animal3 dog = new Dog3();
        // 使用实例对象中的操作
        dog.eat();
        dog.makeSound();

        // 本处原理与上面相同，Cat3类同样是一个实现了Animal3接口的类，但是它们具体的实现可能不同。
        Animal3 cat = new Cat3();
        cat.eat();
        dog.makeSound();
    }

}

/**
 * 通过使用interface关键词来定义 Animal3接口
 */
interface Animal3{
    /**
     * 下面两个方法都是本接口类中定义的方法，这里只有定义好的方法，没有具体的代码实现，也不需要在这里实现
     */
    void eat();
    void makeSound();
}

/**
 * 定义一个Dog3类，并且这个类通过关键词 implements 实现了 Animal3 接口，
 * 在本类中就要具体重写实现 Animal3接口中的所有方法
 */
class Dog3 implements Animal3 {
    /**
     * 这里使用注解@Override 来重写实现eat()方法
     */
    @Override
    public void eat(){
        System.out.println("小狗正在吃饭。。。");
    }

    /**
     * 这里使用注解@Override 来重写实现makeSound()方法
     */
    @Override
    public void makeSound(){
        System.out.println("旺旺旺");
    }
}

/**
 * 再定义一个Cat3类，并且这个类通过关键词 implements 实现了 Animal3 接口，
 * 在本类中就要具体重写实现 Animal3接口中的所有方法
 */
class  Cat3 implements Animal3 {
    /**
     * 这里使用注解@Override 来重写实现eat()方法
     */
    @Override
    public void eat(){
        System.out.println("小猫正在吃饭。。。");
    }

    /**
     * 这里使用注解@Override 来重写实现makeSound()方法
     */
    @Override
    public void makeSound(){
        System.out.println("喵喵喵");
    }
}
