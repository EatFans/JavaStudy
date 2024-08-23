package cn.eatfan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Java 注解
 */
public class Study9 {
    /**
     * Java 注解（Annotation）是一种元数据，可以附加到代码中的类、方法、字段、参数等。注解本身不会对代码执行产生直接的影响，
     * 但可以通过工具或者框架读取并处理这些注解，达到增强功能的效果。
     */
    public static void main(String[] args){
        // 创建Test对象
        Test test = new Test();
        // 调用含有 @Deprecated 注解的方法时候，就会报提醒 方法已弃用，
        // 这个提醒只是提示程序员。提醒说明该方法已经是很老旧的，并非新的，可以用但是不建议
        test.oldMethod();
        // 这个是与上面作对比的正常方法
        test.newMethod();


        /*
            使用自定义注解
         */
        // 使用Test类对象调用test()方法
        test.test();
        // 通过Test类来直接获取Test类的class对象
        Class<Test> testClass = Test.class;
        // 这里因为从class对象中获取方法时候可能会出现异常错误，所以后面操作都要抛出异常
        try{
            // 从Test类的class对象中获取名字叫test的方法，并设置一个变量method
            Method method = testClass.getMethod("test");
            //检查这个方法是否存在有一个注解，这个注解为MyAnnotation，注意isAnnotationPresent()传入的参数是为注解的class对象
            if (method.isAnnotationPresent(MyAnnotation.class)){
                // 在上面检查中成功检查到存在这个注解后，
                // 再从这个方法中get获取这个注解并设置一个变量，注意getAnnotation()传入的参数同样也是注解的class对象
                MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
                // 最后再输出这个注解中定义好的值，获取该方法注解传入进来的值
                System.out.println("MyAnnotation 注解中的值为："+myAnnotation.value());
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

/**
 * 定义一个Test类，用来演示注解的简单使用
 */
class Test{
    /**
     * 使用内置的注解 @Deprecated 来标记这个方法为废弃方法，
     * 同样内置注解有很多种，还有一个常用的内置注解就是@Override 用于方法的重写实现
     */
    @Deprecated
    public void oldMethod(){
        System.out.println("test");
    }

    /**
     * 这是一个正常的方法，没有使用注解 @Deprecated 来标记这个方法为废弃方法
     */
    public void newMethod(){
        System.out.println("Test");
    }

    /**
     * 在这个方法中，使用了自己自定义的一个注解，并给这个注解提供了值，并没有使用默认值
     */
    @MyAnnotation(value = "你好！这是我的一个注解！")
    public void test(){
        System.out.println("test123456");
    }
}


/**
 * 在这里自己定义一个注解 @MyAnnotation ，通过使用关键词 @interface来声明
 * 这里要用注解声明表示自己自定义注解的类型范围和保留时间
 * 注解 @Target 用于指定注解的使用范围，
 * 注解 @Retention 用于指定注解的保留时间。
 */
@Target(ElementType.METHOD) // 使用范围这里定义在方法上
@Retention(RetentionPolicy.RUNTIME)   // 保留时间这里定义为在运行时保留
@interface MyAnnotation{
    /**
     * // 定义一个属性 value，提供默认值
     */
    String value() default "这是注解默认的一个字符串值";
}