package cn.eatfan;

/**
 * Java 父类与子类继承
 *
 */
public class Study1 {

    public static void main(String[] args){
        // 使用Person类型分别创建Student和Teacher类的对象
        Person ming = new Student("小明",100);
        Person mrWang = new Teacher("王老师");

        // 输出小明的名字
        System.out.println(ming.getName());
        // 将小明对象的Person类型强制转化成子类Student类型，并使用子类Student类中的getStudentID()方法，并输出
        System.out.println(((Student) ming).getStudentID());
        // 输出王老师的名字
        System.out.println(mrWang.getName());
        // 将王老师对象的Person类型强制转化为子类Teacher类型，并调用Teacher类中的sayHello()方法
        ((Teacher) mrWang).sayHello();

        System.out.println("   ");

        // 创建一个MathTeacher类的对象，使用的类型为父类的父类的类Person类型，名字为 李老师
        Person li = new MathTeacher("李老师");
        // 将 li 对象类型强制转化为 MathTeacher类型，并使用MathTeacher类从Teacher类继承而来的方法sayHello()
        ((MathTeacher) li).sayHello();
        // 通过Person类型的MathTeacher对象 li 获取名字，然后将Person类型的MathTeacher对象强制转化为MathTeacher类型，使用MathTeacher类中的方法 getDiscipline()获取李老师所教的学科
        System.out.println(li.getName() + "是一位 "+((MathTeacher) li).getDiscipline());

        System.out.println(" ");

        // 创建一个类型为Student类型的 TripleMeritStudent 类的对象 小红
        Student hong = new TripleMeritStudent("小红",101,"高一");
        // 获取这个对象的名字 getName()这个方法是Student类继承Person类获取到的方法，在Student类中并没有去写 name 属性相关操作
        System.out.println(hong.getName());
        // 获取这个对象的studentID，getStudentID()这个方法是Student类中本身有的方法，studentID 属性是Student类中的，并不是通过继承从父类Person类中获取到的
        System.out.println(hong.getStudentID());
        // 获取这个对象的 grade，因为 hong 对象类型为Student类型，而且在创建这个对象时候，是使用到TripleMeritStudent类，所以想使用getGrade()方法，就把这个对象类型强制转化为TripleMeritStudent类型，然后调用getGrade()方法
        System.out.println(((TripleMeritStudent) hong).getGrade());
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

/**
 * Student类继承Person类，继承了Person的属性和方法
 */
class Student extends Person {
    /**
     * 子类Student类的独有属性 studentID，父类并不存在该属性
     */
    private int studentID;

    /**
     * 子类Student的构造方法，其中含有name 和 studentID两个属性的初始化，
     * name 属性通过 super() 调用了父类Person 的构造方法，将父类的属性 name 初始化完成，子类Student 对象也可以调用父类的getName()方法
     * @param name 名字
     * @param studentID 学生iD
     */
    public Student(String name,int studentID) {
        super(name);
        this.studentID = studentID;
    }

    /**
     * 这是子类Student 独有的getter方法，用于来获取本类的studentID 的属性
     * @return 返回本类中的studentID 属性
     */
    public int getStudentID(){
        return studentID;
    }

}

/**
 * 本类TripleMeritStudent 类是作为 Student 类的子类，继承于Student类，然后用于属于自己的属性 grade，继承类Student类的方法和属性
 */
class TripleMeritStudent extends Student {
    /**
     * 本类新的属性grade，父类Student并不存在该属性
     */
    private String grade;

    /**
     * 本类的构造方法，其中的name、studentID属性继承于父类Student，初始化时候使用super()调用父类Student的构造方法，
     * grade 属性作为本类新添加的属性，在本类中初始化
     * @param name 名字
     * @param studentID 学生id
     * @param grade 年级
     */
    public TripleMeritStudent(String name, int studentID,String grade){
        super(name,studentID);
        this.grade = grade;
    }

    /**
     * 获取年级，这个方法是本类新添加的方法，只能在本类对象或继承本类的对象才能调用该方法，像父类Student就无法调用本方法
     * @return 返回属性 年级
     */
    public String getGrade(){
        return grade;
    }
}

/**
 * Teacher类继承父类Person，继承类父类Person的方法
 */
class Teacher extends Person {
    /**
     * 子类Teacher的构造方法，其中name，用于去初始化继承的父类的name属性，通过super()来调用父类的构造函数初始化name属性
     * @param name 名字
     */
    public Teacher(String name){
        super(name);
    }

    /**
     * 子类Teacher独有的方法，这个方法在子类Teacher类型的对象进行调用，如果Teacher类作为父类，其他类继承类Teacher类，
     * 那么这个类也会继承Teacher类的sayHello()方法
     */
    public void sayHello(){
        System.out.println("Hello from teacher.");
    }
}

/**
 * 本类MathTeacher类是Teacher类的子类，MathTeacher类继承父类Teacher类，同时继承类父类的属性和方法，
 * 在本类又添加了新的属性和方法，只有本类和继承本类的对象使用，其他类型无法使用本类类型，如父类Teacher和父类的父类Person
 */
class MathTeacher extends Teacher{
    /**
     * 本类新添加独属于本类的属性，学科
     */
    private final String discipline;

    /**
     * 这是本类MatherTeacher的构造方法，在这个构造方法初始化了属性name，这个属性继承于父类Teacher，
     * 通过super()调用了父类Teacher 的构造方法
     * @param name 名字
     */
    public MathTeacher(String name){
        super(name);
        this.discipline = "数学";
    }

    /**
     * 这是本子类新添的方法，获取学科名称，这个方法只能允许对象类型为MathTeacher类或继承了MathTeacher类的子类使用本方法，其他类型的类对象无法使用
     * @return 返回本类中的属性 discipline
     */
    public String getDiscipline(){
        return discipline;
    }
}