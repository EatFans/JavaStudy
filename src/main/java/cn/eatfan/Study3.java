package cn.eatfan;

/**
 * Java 多态
 */
public class Study3 {

    /**
     *  Java 多态的定义：
     *  多态是指同一个方法调用在不同的对象上有不同的行为表现。在 Java 中，
     *  多态通常通过继承和接口来实现。当一个父类引用指向子类对象时，
     *  通过该引用调用的方法会根据实际对象的类型来执行对应的方法。
     *
     *  Java 多态的实现：
     *  1.	通过继承实现多态：
     * 	•	父类引用可以指向子类对象。
     * 	•	通过父类引用调用的方法在运行时会根据实际对象类型来决定调用哪个方法。
     * 	2.	通过接口实现多态：
     * 	•	接口引用可以指向实现该接口的类的对象。
     * 	•	通过接口引用调用的方法在运行时会根据实际对象类型来决定调用哪个方法。
     */
    public static void main(String[] args){
        /*
            通过继承父类来实现多态效果
         */

        // 父类引用指向子类对象
        Animal2 myAnimal = new Animal2();  // Animal类型的对象
        Animal2 myDog = new Dog2();        // Dog类型的对象，但引用类型是Animal
        Animal2 myCat = new Cat2();        // Cat类型的对象，但引用类型是Animal

        // 调用makeSound方法
        myAnimal.makeSound();  // 输出：动物在叫。。。
        myDog.makeSound();     // 输出：汪汪汪
        myCat.makeSound();     // 输出：喵喵喵

        //myDog 和 myCat 变量的类型是 Animal，但它们实际引用的是 Dog 和 Cat 对象，
        // 当调用 myDog.makeSound() 时，尽管 myDog 的类型是 Animal，但由于它实际引用的是 Dog 对象，因此会调用 Dog 类中的 makeSound 方法。
        // 这就是多态的体现：父类引用可以根据实际对象类型的不同而表现出不同的行为。

        System.out.println(" ");
        /*
            通过接口实现多态的效果
         */
        // 接口引用指向实现类的对象
        Shape myCircle = new Circle();     // Circle类型的对象，但引用类型是Shape
        Shape myRectangle = new Rectangle(); // Rectangle类型的对象，但引用类型是Shape

        // 调用draw方法
        myCircle.draw();      // 输出：画一个圆圈
        myRectangle.draw();   // 输出：画一个矩形

        // Shape 是一个接口，而 Circle 和 Rectangle 都实现了这个接口，
        // myCircle 和 myRectangle 变量的类型是 Shape，但它们实际引用的是 Circle 和 Rectangle 对象。
        // 当调用 myCircle.draw() 时，尽管 myCircle 的类型是 Shape，但由于它实际引用的是 Circle 对象，因此会调用 Circle 类中的 draw 方法。
    }

}

/*
 *  通过继承实现多态
 */

/**
 * 定义个Animal2类，因为在之前类中已经定义过Animal类，所以这里为了方便，就使用Animal2来命名本类，
 * Animal2类是作为一个父类，用于被子类来继承的存在
 */
class Animal2 {
    /**
     * makeSound()方法是Animal2类的一个操作的方法
     */
    public void makeSound(){
        System.out.println("动物在叫。。。");
    }
}

/**
 * Dog2类继承了父类Animal2类，作为Animal2类的子类存在
 */
class Dog2 extends Animal2 {
    /**
     * 在本类中，这里重写了从父类继承的makeSound()方法，
     * 重新实现了本类中对makeSound()方法的实现效果
     */
    @Override
    public void makeSound(){
        System.out.println("汪汪汪");
    }
}

/**
 * Cat2类继承类父类Animal2类，作为Animal2类的子类存在
 */
class Cat2 extends Animal2 {
    /**
     * 在本类中，这里重写类从父类继承的makeSound()方法，
     * 重新实现了本类中对makeSound()方法的实现效果
     */
    @Override
    public void makeSound(){
        System.out.println("喵喵喵");
    }
}


/*
 *  通过接口来实现多态
 */

/**
 * 定义一个接口类Shape，只提供方法，不提供实现效果
 */
interface Shape{
    /**
     * 在这个接口类中提供了一个draw()的方法，这里也只是提供了方法，并没有提供实现
     */
    void draw();
}

/**
 * 定义一个Circle 类，并且来实现Shape接口类
 */
class Circle implements Shape {
    /**
     * 这个方法，重写Shape接口类中的draw()方法，并具体的实现这个方法
     */
    @Override
    public void draw() {
        System.out.println("画一个圆圈");
    }
}

/**
 * 定义一个Rectangle类，并且来实现Shape接口类
 */
class Rectangle implements Shape {
    /**
     * 这个方法重写Shape接口类中的draw()方法，并具体的实现这个方法
     */
    @Override
    public void draw() {
        System.out.println("画一个矩形");
    }
}
