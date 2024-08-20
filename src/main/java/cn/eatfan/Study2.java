package cn.eatfan;

/**
 * Java 重载与重写
 */
public class Study2 {
    public static void main(String[] args) {
        /*
         * 重载
         */

        // 将 MathOperations 类对象创建出来
        MathOperations mathOperations = new MathOperations();

        // 调用没有含有参数，也没有任何效果只会返回0的add()方法，并将结果输出
        int add1 = mathOperations.add();
        System.out.println("add() 输出的结果："+add1);

        // 调用含有两个整型类型的参数，让两个值相加，并返回相加值的add()方法，并将结果输出
        int add2 = mathOperations.add(1,2);
        System.out.println("add(int a,int b) 输出的结果："+add2);

        // 调用含有三个整型类型的参数，让三个值相加，并返回相加的add()方法，并将结果输出
        int add3 = mathOperations.add(1,2,3);
        System.out.println("add(int a,int b,int c) 输出的结果是："+add3);

        // 调用含有两个浮点类型的参数，让两个值相加，并返回相加的add()方法，并将结果输出
        double add4 = mathOperations.add(1.1,2.2);
        System.out.println("add(double a,double b) 输出的结果是："+add4);

        // 上面这些就是重载实际实现和应用，具体怎么用，要看实际项目情况
        // 重载，可以让你在同一个类中使用同一个函数名，传入不同的参数，达到不同的目的的效果
        // 例如，我要写一个加法方法，同样的名字我可以写一个有两个整型参数的，可以写有多个整型参数的，可以写浮点类型参数的等等，
        // 然后在实际调用这个加法方法时候，可以通过更改方法传入参数，达到换方法执行的效果。不需要再去另起名字另外再写一份新的方法。


        /*
         *   重写
         * 重写的定义：
         * 重写是指子类重新定义父类中已存在的方法。重写的方法必须具有相同的方法名、参数列表和返回类型。子类可以修改父类方法的实现，但不能改变方法的签名（即方法名、参数类型和数量）。
         *
         * 重写的应用：
         * 重写用于实现多态，使得通过父类引用调用子类的实现，可以动态地决定调用哪个具体的方法
         */

        // 创建一个正常Animal对象
        Animal animal = new Animal("动物");
        System.out.println(animal.getName());
        //再用这个对象执行Animal类中eat()方法
        animal.eat();

        System.out.println(" ");

        // 创建类型为Animal的Dog对象和Cat对象
        Animal dog = new Dog("旺财");
        Animal cat = new Cat("咪咪");

        // 输出小狗名字，再使用小狗对象操作eat()方法
        System.out.println("小狗名字为： "+dog.getName());
        dog.eat();

        System.out.println(" ");

        // 输出小猫名字，再使用小猫对象操作eat()方法
        System.out.println("小猫名字为： "+cat.getName());
        cat.eat();

        // 上面三个对象创建后，同样都是操作对象执行eat()方法，都是相同Animal类型，但是输出的结果却不是相同
        // 因为在这里，Dog类和Cat类中继承了Animal类后，同时继承了eat()方法，再继承到eat()方法后，
        // Dog类和Cat类中分别重新实现了各种的eat()方法，改写了各种的方法实现，这就叫做重写，再声明这些重写的类对象时候，
        // 执行重写过的方法，实际执行就跟父类继承到的完全不同了
    }
}

/**
 * MathOperations类是自定义的一个数学操作类，本类用于来展示重载的具体定义和应用
 * 重载的定义：
 * 重载是指在同一个类中，可以定义多个方法名相同但参数列表不同的方法。方法的参数列表不同可以包括参数的数量、
 * 类型或顺序不同。重载的方法可以具有不同的返回类型，但返回类型不能作为重载的唯一依据。
 * 重载的应用：
 * 重载可以提供不同的方式来执行相似的操作，增加了方法的灵活性和可读性。
 */
class MathOperations {
    /**
     * 这是一个本类中的一个加分方法，这个方法没有任何操作，同时也没有任何参数传入，是作为一个定义好的无用方法，只有一个单纯的返回整型0
     * @return 返回整型值0
     */
    public int add() {
        return 0;
    }

    /**
     * 在上面其实已经定义了一个add()方法，但是没有任何参数传入，也没有任何效果，如果我们想再写一个相同的方法，给这个再写一条传入参数的同名方法，
     * 以下这样形式也是可以的，在一个类中，可以出现同名不同参的方法，本方法就是重载一个新的add()方法，只不过这里传入类两个参数a、b
     * @param a a
     * @param b b
     * @return 返回 a + b 的结果
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * 这是重载一个传入三个参数的同名方法，同上。
     * @param a a
     * @param b b
     * @param c c
     * @return 返回 a + b + c 的结果
     */
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    /**
     * 这是重载一个传入两个双浮点类型的参数的同名方法，同上。
     * @param a a
     * @param b b
     * @return 返回 a + b 的结果
     */
    public double add(double a, double b) {
        return a + b;
    }
}

/**
 * 这是一个动物类，在这里作为父类，提供了一个最简单基础的属性 name，
 * Animal的构造函数，简单初始化了属性的值，然后提供了name属性的getter，
 * 然后还声明实现了一个Animal类的eat()方法，作为父类的方法供子类继承。
 */
class Animal {
    private String name;

    /**
     * Animal类的构造方法，简单的初始化属性name的值
     * @param name 名字
     */
    public Animal(String name){
        this.name = name;
    }

    /**
     * Animal 类属性name 的getter
     * @return 返回name
     */
    public String getName(){
        return name;
    }

    /**
     * 这是本类中，自定义实现的一个eat()方法
     */
    public void eat(){
        System.out.println("动物正在吃东西。。。");
    }
}

/**
 * Dog类继承了Animal类，作为Animal类的子类，继承了Animal类中的所有属性和方法。
 */
class Dog extends Animal {
    /**
     * Dog类的构造方法，设置参数name，将这个参数通过super()调用父类的构造方法，给属性name 初始化
     * @param name name
     */
    public Dog(String name){
        super(name);
    }

    /**
     * 这个eat()方法本身是从父类Animal类中继承过来的，并不需要实现，因为再父类中已经有实现了，但是我们要重写这个方法，
     * 所以我们在这里写上相同的方法名和参数，加上注解@Override，用来重写eat()方法的实现，
     * @Override 注解是Java 本身自带的一个注解，用于重写从父类那继承过来的方法，在方法上加上这个，就代表这个方法是重写的重新实现的，
     * 并非是父类那原封不动的，我们可以在这里重新实现该方法，然后这个类创建完对象后调用这个方法时候，使用的效果并非父类原来的实现，
     * 而是自己重写的方法的实现。
     */
    @Override
    public void eat(){
        System.out.println("小狗正在吃东西。。。");
    }
}

/**
 * Cat类继承Animal类，作为Animal类的子类，继承了Animal类的属性和方法
 */
class Cat extends Animal {
    /**
     * Cat类的构造方法，设置参数name，将这个参数通过super()调用父类的构造方法，给属性name 初始化
     * @param name name
     */
    public Cat(String name){
        super(name);
    }

    /**
     * 这个方法原理同上面 Dog类中的eat()方法
     */
    @Override
    public void eat(){
        System.out.println("小猫正在吃东西。。。");
    }
}