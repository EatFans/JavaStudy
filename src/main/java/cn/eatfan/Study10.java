package cn.eatfan;


import java.util.*;

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
        // List 中的元素是有序的，然后是可以重复添加相同的元素，其重复的元素先后顺序不相同

        System.out.println(" ");   // 分界线

        /*
            关于 Set 使用以及实例
         */
        // 创建一个 HashSet 实例对象，其中这个Set容器存放数据的类型为String
        Set<String> stringSet = new HashSet<>();

        // 给这个存放String类型数据的Set添加一些元素
        stringSet.add("Apple");
        stringSet.add("Banana");
        stringSet.add("Orange");
        stringSet.add("Banana"); // Set 是不可以重复添加相同的元素，这样虽然不会报错，但是会弹出警告

        // 在Set中是不允许存放重复的元素的，如果向这个容器存放两个相同元素，最后两个相同的元素只有一个会被存放进去
        // 在上面存放了四个元素，有两个相同的，最后实际上只存放了三个元素

        // 通过for循环遍历 Set 容器
        for (String string : stringSet){
            System.out.println(string);
        }

        // 检查 Set 中是否含有某个元素
        if (stringSet.contains("Apple")){
            System.out.println("这个Set容器中存在元素 \"Apple\"");
        }

        // 删除Set容器中的某一个元素，然后最后遍历这个Set容器，将里面所有的元素打印输出出来
        stringSet.remove("Banana");
        for (String string : stringSet){
            System.out.println(string);
        }
        // Set 中的元素是无序的，不可以重复添加相同的元素，每个元素只会存在一个

        System.out.println(" ");  // 分界线

        /*
            关于 Map 使用以及实例
         */
        // 创建一个 Hashmap 的实例对象，其中Map容器存放以键对值的方式存放数据，这里是String类型为键，Integer类型为值的
        Map<String,Integer> stringIntegerMap =  new HashMap<>();
        // 向 Map 中添加键值对
        stringIntegerMap.put("Apple", 3);
        stringIntegerMap.put("Banana", 5);
        stringIntegerMap.put("Orange", 2);
        stringIntegerMap.put("Banana", 4); // 如果键重复，新值会覆盖旧值

        // 通过某个键来获取对应键的值
        System.out.println(stringIntegerMap.get("Apple"));

        // 通过for循环遍历 Map 容器，打印并输出出来
        for (Map.Entry<String,Integer> entry : stringIntegerMap.entrySet()){
            System.out.println(entry.getKey() + ":" +entry.getValue());
        }

        System.out.println(" ");  // 分界线

        // 检查 Map 容器中是否存在某个键
        if (stringIntegerMap.containsKey("Apple")){
            System.out.println("这个Map容器中存在键：Apple");
        }

        System.out.println(" ");   // 分界线

        // 删除 Map 容器中的某个键值对，并遍历循环打印输出出来
        stringIntegerMap.remove("Banana");
        for (Map.Entry<String,Integer> entry : stringIntegerMap.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        // Map 中的键是唯一的，但值可以重复
    }
}
