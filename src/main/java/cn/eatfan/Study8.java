package cn.eatfan;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Java 反射
 */
public class Study8 {
    public static void main(String[] args){
        /*

            通过反射来创建实例对象，并调用该实例对象中的方法

         */
        // 获取Person2的class对象，将其存为一个值，值类型为Class<?>，
        // 其实这个值类型可以直接设置为Class<Person2>的，因为我们是明确知道值为什么类型的，
        // 但是如果我们不知道值是什么类型，我们可以让这个变成通用的类型——Class<?>类型，
        // 这样不使用具体数据类型，而使用一种通用类型来进行程序设计的方法，我们称之为——泛型编程
        // 在这里，我就将其类型使用为Class<?>类型，我这里只是为了方便，具体项目时候，
        // 要根据实际项目需求、情况、代码逻辑等等方法去选择。切勿被局限了思维。
        Class<?> aClass = Person2.class;

        // 这里的try异常抛出为什么要呢？请继续往下看
        try {
            /*
               这一行是获取类变量aClass中的构造方法
               在已经获取到class对象的中，可以通过上面获取到的aClass变量，
               操作这个变量，这里操作aClass变量调用了一个方法getConstructor()，来获取该类中的构造方法，
               这个方法后面的参数是对应着，想获取构造方法的类中的构造方法的参数数据类型的class对象，
               说人话就是，下面Person2类中构造方法中的参数为String和int类型，然后这里就要添String.class和int.class，
               其他的自己一次类推，自己试试。
               还有一点，使用getConstructor()方法时候会出现一个异常，也就是上面提到的try异常操作，这是你要抛出异常，作处理就行
             */
            Constructor<?> constructor = aClass.getConstructor(String.class,int.class);
            /*
                这里是通过上面已经获取的class对象中的构造方法，进行调用，来创建一个class对象对应的类的对象，
                也就是本实例中的Person2类的对象，这里获取到的Person2类的对象为Java中的Object对象
             */
            Object ming = constructor.newInstance("小明", 18);
            // 调用Person2通过反射创建的实例对象的方法和操作，
            // 在这里通过使用类型的强制转化，将Object类型转化为Person2类型，
            // 然后就可以正常调用Person2类对象中的getName()和getAge()方法，
            // 获取到名字和年龄后，输出
            System.out.println(((Person2) ming).getName());
            System.out.println(((Person2) ming).getAge());


        } catch (Exception e){
            System.out.println(e.getMessage());
        }


        /*

            获取一个类的class对象的方法

         */
        // 在上面代码中，是简单实现了一个通过反射来创建实例对象并调用方法的例子，你会发现我并没有详细说明，获取class对象方式和方法，
        // 下面就详细通过代码讲解class对象获取的方式和方法。
        // 在Java中每个类早JVM中都有一个与之相关的class对象。

        // 1、通过类的字面量
        // 这里以Person2类为例
        // 如同下面一样。这样就直接可以获取该类的class对象
        Class<Person2> person2Class = Person2.class;
        // 2、通过Class.ForName()方法
        // 通过Class类中的静态方法来通过字符串比较获取该类class对象
        // 因为通过字符串获取class对象可能会出现字符串输入错误、软件包路径错误、找不到该类等错误，
        // 这里需要try来抛出ClassNotFoundException的异常错误
        try {
            Class<?> stringClass = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 3、通过对象实例来获取该对象的class对象
        String string = "Hello World";
        Class<?> stringClass = string.getClass();

        System.out.println(" ");
        /*

            通过反射来进行操作

         */
        // 获取Bus类的Class对象
        Class<Bus> busClass = Bus.class;

        try {
            // 通过Bus类的Class对象获取构造方法，参数类型为String和int
            Constructor<?> constructor = busClass.getConstructor(String.class, int.class);

            // 通过反射使用构造方法创建Bus类的实例，传入构造参数"Toyota"和2024
            Object bus = constructor.newInstance("Toyota", 2024);

            // 获取Bus类中的字段（属性）model的Field对象
            Field modelField = busClass.getDeclaredField("model");

            // 获取Bus类中的字段（属性）year的Field对象
            Field yearField = busClass.getDeclaredField("year");

            // 设置字段可访问，即使它是private的
            modelField.setAccessible(true);
            yearField.setAccessible(true);

            // 打印原始字段值，即未修改前的值
            System.out.println("原始 Model 的值: " + modelField.get(bus)); // 获取并打印bus对象的model字段的值
            System.out.println("原始 Year: 的值" + yearField.get(bus));    // 获取并打印bus对象的year字段的值

            // 使用反射修改bus对象的model字段的值为"Honda"
            modelField.set(bus,"Honda");

            // 使用反射修改bus对象的year字段的值为2023
            yearField.set(bus,2023);

            // 打印修改后的字段值
            System.out.println("更新后 Model 的值: " + modelField.get(bus)); // 获取并打印修改后的model字段的值
            System.out.println("更新后 Year: 的值" + yearField.get(bus));    // 获取并打印修改后的year字段的值

            // 获取Bus类中名为start的方法的Method对象，该方法无参数
            Method start = busClass.getMethod("start");

            // 通过反射调用bus对象的start方法
            start.invoke(bus);

        } catch (Exception e) {
            // 捕获所有可能的异常并打印错误信息
            System.out.println(e.getMessage());
        }

        // 这是关于反射大体的用法，具体更详细的用法和操作，请参考文档或等待后面更新
    }
}

/**
 * 这是一个封装好用于测试实验反射的Person2类
 */
class Person2 {
    private String name;
    private int age;
    public Person2(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

}

/**
 * 这是一个封装好用于测试实验反射的Person2类
 */
class Bus {
    private String model;
    private int year;

    public Bus(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public void start() {
        System.out.println("公交车正在启动。。。");
    }
}
