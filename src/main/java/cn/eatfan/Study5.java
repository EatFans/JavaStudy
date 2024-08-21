package cn.eatfan;

/**
 * Java 封装
 */
public class Study5 {
    public static void main(String[] args){
        User user = new User();
        user.setId(10001);
        user.setUsername("test");
        user.setPassword("123456");
        user.setEmail("123456@teat.com");
        user.setAge(22);

        System.out.println("用户id："+user.getId());
        System.out.println("用户名："+user.getUsername());
        System.out.println("用户密码："+user.getPassword());
        System.out.println("用户邮箱："+user.getEmail());
        System.out.println("用户年龄："+user.getAge());
    }
}

class User {
    private long id;
    private String username;
    private String password;
    private String email;
    private int age;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }
}
