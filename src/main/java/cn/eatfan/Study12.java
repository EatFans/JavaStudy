package cn.eatfan;

/**
 * Java 自定义异常
 */
public class Study12 {
    public static void main(String[] args){
        try {
            // 因为User2类的构造方法中调用了setAge()方法，而setAge()方法中做了一个年龄检查，
            // 如果年龄不合法就会抛出自己定义的 InvalidAgeException 异常，
            // 所以，这里在创建User2实例对象的时候，要用try-catch包围
            User2 ming = new User2("小明", 12);  // 这里的年龄，设置为大于等于18岁就可以正常创建User2实例对象，但是小于18岁，就会抛出InvalidAgeException异常
            System.out.println("用户创建成功！"+ming.getName());
        } catch (InvalidAgeException e) {
            System.out.println("捕获到异常："+e.getMessage());
        }
    }
}

/**
 * 在这里定义一个 InvalidAgeException 类，InvalidAgeException 意思为 无效年龄异常错误，
 * 这个类在这里继承了Java 中的异常类Exception类，
 */
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message){
        super(message);
    }
}

/**
 * 定义一个User2类，用于测试自己定义的InvalidAgeException异常
 */
class User2 {
    /**
     * 下面是封装在类中的属性和方法，在这里就不多讲解，想了解，可以看目录Java 封装中的说明
     */
    private String name;
    private int age;

    // 构造方法
    public User2(String name, int age) throws InvalidAgeException {
        this.name = name;
        setAge(age); // 设置年龄时可能抛出自定义异常
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // 设置年龄的方法
    public void setAge(int age) throws InvalidAgeException {
        if (age < 18) {
            // 如果年龄小于18岁，抛出自定义异常
            throw new InvalidAgeException("年龄必须大于或等于18岁。");
        }
        this.age = age;
    }
}