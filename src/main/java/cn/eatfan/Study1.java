package cn.eatfan;

/**
 * Java 父类与子类继承
 *
 */
public class Study1 {

    public static void main(String[] args){
        Person Ming = new Student("小明",100);
        Person MrWang = new Teacher("王老师");

        System.out.println(Ming.getName());
        System.out.println(((Student) Ming).getStudentID());
        System.out.println(MrWang.getName());

    }

}

/**
 * 父类 Person 这里面只有一个基础属性name和一个获取name的方法
 */
class Person {
    private String name;

    /**
     * 构造方法来初始化Person类的属性name
     * @param name
     */
    public Person(String name){
        this.name = name;
    }

    /**
     * 这是一个对于属性name的getter
     * @return 返回本类的属性name
     */
    public String getName(){
        return name;
    }
}

class Student extends Person {
    private int studentID;
    public Student(String name,int studentID) {
        super(name);
        this.studentID = studentID;
    }
    
    public int getStudentID(){
        return studentID;
    }

}

class Teacher extends Person {
    public Teacher(String name){
        super(name);
    }
}