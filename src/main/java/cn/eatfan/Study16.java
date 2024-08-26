package cn.eatfan;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Java 动态代理
 */
public class Study16 {
    public static void main(String[] args){
        /*
            Java 动态代理是一种允许你在运行时动态地创建代理对象，并能够拦截方法调用的机制。
            它常用于实现一些横切关注点（如日志记录、事务管理等），而无需修改实际业务代码。
            动态代理主要通过 java.lang.reflect.Proxy 类和 java.lang.reflect.InvocationHandler 接口实现。

         */
        // 创建实际的服务对象
        UserService userService = new UserServiceImpl();

        // 使用动态代理创建代理对象
        UserService proxy = (UserService) UserServiceProxy.createProxy(userService);

        // 通过代理对象调用方法
        proxy.addUser("Alice", "password123");
        proxy.deleteUser("Alice");

        /*
            动态代理的应用场景:
	        AOP（面向切面编程）：如在 Spring 框架中，动态代理广泛用于实现 AOP，帮助开发者在不修改业务代码的情况下添加日志、事务管理、性能监控等功能。
	        远程服务调用：在一些分布式系统中，动态代理常用于客户端调用远程服务时的代理对象生成。
	        代理模式的实现：动态代理是代理模式的一种实现方式，特别适用于代理的行为在运行时需要动态决定的场景。
         */
    }
}

/**
 * 定义一个UserService接口类
 */
interface UserService {
    void addUser(String username, String password);
    void deleteUser(String username);
}

/**
 * 定义一个UserServiceImpl类实现UserService接口
 */
class UserServiceImpl implements UserService {
    @Override
    public void addUser(String username, String password) {
        System.out.println("User added: " + username);
    }

    @Override
    public void deleteUser(String username) {
        System.out.println("User deleted: " + username);
    }
}

/**
 * 定义一个UserServiceProxy类实现InvocationHandler接口，用于创建动态代理类
 */
class UserServiceProxy implements InvocationHandler {
    // 持有目标对象的引用
    private final Object target;

    // 通过构造函数传递目标对象
    public UserServiceProxy(Object target) {
        this.target = target;
    }
    // 代理对象调用方法时实际执行的逻辑
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在方法执行之前添加日志
        System.out.println("Method " + method.getName() + " is called with args: " + java.util.Arrays.toString(args));

        // 调用目标对象的实际方法
        Object result = method.invoke(target, args);

        // 在方法执行之后添加日志
        System.out.println("Method " + method.getName() + " execution completed");

        return result;
    }

    // 创建代理对象的方法
    public static Object createProxy(Object target) {
        // 获取目标对象的类加载器和接口
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();

        // 创建代理对象
        return Proxy.newProxyInstance(classLoader, interfaces, new UserServiceProxy(target));
    }
}