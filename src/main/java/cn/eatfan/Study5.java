package cn.eatfan;

/**
 * Java 封装
 */
public class Study5 {
    /**
     * 封装是面向对象编程（OOP）的四大基本特性之一，其主要目的是将对象的状态（属性）隐藏起来，仅通过公开的方法来访问和修改这些属性。这有助于保护对象的完整性，防止外部直接对其状态进行不合理的修改。
     */
    public static void main(String[] args){
        // 创建一个封装好的用户实例
        User user = new User();

        // 分别设置好用户数据
        user.setId(10001);
        user.setUsername("test");
        user.setPassword("123456");
        user.setEmail("123456@teat.com");
        user.setAge(22);

        // 分别依次输出用户信息数据
        System.out.println("用户id："+user.getId());
        System.out.println("用户名："+user.getUsername());
        System.out.println("用户密码："+user.getPassword());
        System.out.println("用户邮箱："+user.getEmail());
        System.out.println("用户年龄："+user.getAge());
    }
}

/**
 * 定义个User类，用于来封装用户数据和用户的一些操作
 */
class User {
    /**
     * 下面这些都是封装好的数据属性
     */
    private long id; // 用户id
    private String username; // 用户名
    private String password; // 用户密码
    private String email; // 用户邮箱
    private int age;  // 用户年龄

    /**
     * 这是一个get方法，用于获取用户id
     * @return 返回用户id
     */
    public long getId(){
        return id;
    }

    /**
     * 这是一个set方法，用于设置用户id
     * @param id 用户id
     */
    public void setId(long id){
        this.id = id;
    }

    /**
     * 这是一个get方法，用于获取用户名
     * @return 返回用户名
     */
    public String getUsername(){
        return username;
    }

    /**
     * 这是一个set方法，用于设置用户名
     * @param username 用户名
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * 这是一个get方法，用于获取用户密码
     * @return 返回用户密码
     */
    public String getPassword(){
        return password;
    }

    /**
     * 这是一个set方法，用于设置用户密码
     * @param password 用户密码
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * 这是一个get方法，用于获取用户邮箱
     * @return 返回用户邮箱
     */
    public String getEmail(){
        return email;
    }

    /**
     * 这是一个set方法，用于设置用户邮箱
     * @param email 用户邮箱
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * 这是一个get方法，用于获取用户年龄
     * @return 返回用户年龄
     */
    public int getAge(){
        return age;
    }

    /**
     * 这是一个set方法，用于设置用户年龄
     * @param age 用户年龄
     */
    public void setAge(int age){
        this.age = age;
    }
}
