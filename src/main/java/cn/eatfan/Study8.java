package cn.eatfan;

import java.lang.reflect.Constructor;

/**
 * Java 反射
 */
public class Study8 {
    public static void main(String[] args){
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

             */
            Person2 ming = (Person2) constructor.newInstance("小明", 18);

            System.out.println(ming.getName());

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

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
