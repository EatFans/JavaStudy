package cn.eatfan;


import java.util.ArrayList;
import java.util.List;

/**
 *  Java List、Set、Map
 */
public class Study10 {
    public static void main(String[] args){
        // 在 Java 中，List、Set 和 Map 是三种常用的集合接口，它们分别用于存储不同类型的数据结构

        /*
            关于 List 使用以及实例
         */
        // 创建一个 ArrayList 实例对象，其中这个List容器存放数据的类型为String类型
        List<String> stringList = new ArrayList<>();

        // 给这个存放String类型数据的List添加一些元素
        stringList.add("Hello World");
        stringList.add("Hello List");
        stringList.add("你好！世界！");
        stringList.add("Hello List"); // List 可以重复添加相同的元素

        // 通过索引来访问获取该List容器中的元素并打印输出出来，第一个元素以0开始
        System.out.println(stringList.get(0));
        System.out.println(stringList.get(1));
        System.out.println(stringList.get(2));
        System.out.println(stringList.get(3));

        System.out.println(" ");  // 分界线

        // 通过遍历List容器来一次性输出所有的元素
        for(String string : stringList){
            System.out.println(string);
        }

        System.out.println(" ");  // 分界线

        // 删除该List容器中的一个元素,
        // 在这里因为前面添加元素时候，“Hello List"元素是重复添加的，
        // 这里删除的是第一个“Hello List"元素
        stringList.remove("Hello List");
        // 打印输出删除后List容器中的所有元素
        for (String string : stringList){
            System.out.println(string);
        }


    }
}
