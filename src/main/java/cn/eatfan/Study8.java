package cn.eatfan;

import java.lang.reflect.Constructor;

/**
 * Java 反射
 */
public class Study8 {
    public static void main(String[] args){
        Class<?> aClass = Person2.class;

        try {
            Constructor<?> constructor = aClass.getConstructor(String.class,int.class);
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
