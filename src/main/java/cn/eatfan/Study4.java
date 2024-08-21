package cn.eatfan;

/**
 * Java 抽象类
 */
public class Study4 {
    public static void main(String[] args){
        /*
            抽象类的定义：
            •	抽象类是不能实例化的类，通常用来表示一种抽象的概念或行为。
	        •	抽象类可以包含抽象方法（没有实现的方法）和非抽象方法（已经实现的方法）。
	        •	子类继承抽象类后，必须实现所有抽象方法，除非子类也是抽象类。
            抽象类的应用：
            •	当多个类有一些共同行为或属性，但这些行为或属性无法在父类中完全实现时，可以将这些类的共性提取出来放到抽象类中。
	        •	抽象类可以为子类提供通用的实现，同时强制子类实现特定的行为。

         */

        // Vehicle 类是一个抽象类，它不可以创建实例对象，下面就是一个创建抽象类实例对象的代码，你可以去掉其注释，
        // 你就会发现，编译器IDE将会报错，提示你：Vehicle 无法实例化，
        // 抽象类只有被继承，继承抽象类的子类可以正常实例化创建对象。

//        Vehicle vehicle = new Vehicle("Toyota", "Corolla", 2024);

        // Car 类是 Vehicle 抽象类的子类，Car类可以正常初始化创建实例对象，如下：
        Car car = new Car("Toyota", "Corolla", 2024);

        // 通过getter方法获取抽象类中的属性值，并将这些属性打印出来
        System.out.println("Brand: " + car.getBrand());
        System.out.println("Model: " + car.getModel());
        System.out.println("Year: " + car.getYear());

        // 调用car对象的startEngine()方法
        car.startEngine();
    }
}

/**
 * 定义一个Vehicle的抽象类
 */
abstract class Vehicle {
    /**
     * 下面是这个抽象类的一些属性，这些属性只能在这个抽象类中使用
     */
    private String brand;
    private String model;
    private int year;

    /**
     * 这是Vehicle抽象类的构造方法，用于来初始化本类中的各种属性的值
     * @param brand brand
     * @param model model
     * @param year year
     */
    public Vehicle(String brand,String model,int year){
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    /**
     * 这是Vehicle 抽象类中的一个抽象方法，这里只是定义了方法，并没有实际实现的代码，
     * 在这里 abstract 修饰符就是定义抽象的关键字，在这里表面，startEngine()方法为一个抽象方法，
     * 这个抽象方法，要在本类Vehicle类被子类继承后，在子类中重写实现
     */
    public abstract void startEngine();

    /**
     * 这是一个非抽象方法的getter方法，用于获取本类属性 brand
     * @return 返回属性 brand
     */
    public String getBrand(){
        return brand;
    }

    /**
     * 这是一个非抽象方法的getter方法，用于获取本类属性 model
     * @return 返回属性 model
     */
    public String getModel(){
        return model;
    }

    /**
     * 这是一个非抽象方法的getter方法，用于获取本类属性 year
     * @return 返回属性 year
     */
    public int getYear(){
        return year;
    }
}

/**
 * 定义 Car类，Car类作为子类继承了 Vehicle 抽象类
 */
class Car extends Vehicle {
    /**
     * Car类的构造方法，用来初始化属性，通过super()来调用Vehicle类中的构造方法，初始化属性
     * @param brand brand
     * @param model model
     * @param year year
     */
    public Car(String brand,String model, int year){
        super(brand,model,year);
    }

    /**
     * 本方法是本类Car类继承父类Vehicle抽象类中获取到的一个抽象方法，在父类Vehicle类中本方法被定义为一个抽象方法，
     * 只提供了方法的定义，却没有具体方法的实现，所以，在子类继承了后，子类中就要去重写实现父类中的所以抽象方法，
     * 在这个方法中，就是通过注解 @Override ，来表示这个方法重写重新实现，然后重写这个方法的代码实现
     */
    @Override
    public void startEngine() {
        System.out.println("汽车正在启动引擎。。。");
    }
}
