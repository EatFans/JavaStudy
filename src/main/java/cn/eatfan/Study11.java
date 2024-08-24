package cn.eatfan;

/**
 * Java try-catch-finally 异常捕获与处理
 */
public class Study11 {
    public static void main(String[] args){
        // 如果直接调用divide()方法，将a和b传入参数，如果b = 0,运行时候就会报错，出现 ArithmeticException 的异常
        //        int c = divide(1,0);
        // 下面是通过 try-catch-finally 捕获异常，然后处理异常的实例
        try{
            int c = divide(1,0);    // 这是b传入的参数是 0 ，就会导致异常错误，无法正常运行程序
            System.out.println("计算结果为：" + c);
        } catch (ArithmeticException e) {
            // 捕获特定的ArithmeticException异常并处理
            System.out.println("捕获到异常: " + e.getMessage());
        } catch (Exception e) {
            // 捕获其他所有异常并处理
            System.out.println("捕获到一个未知异常: " + e.getMessage());
        } finally {
            // 无论是否发生异常，finally块中的代码都会执行
            System.out.println("程序执行结束。无论是否有异常，都会执行这里。");
        }

        // 即使发生异常，程序依然会继续执行到这里
        System.out.println("这行代码在try-catch-finally之后执行。");
    }

    // 一个简单的除法方法，用来演示可能会抛出异常的情况

    /**
     * 这是一个简单的除法方法，用来演示可能会抛出异常的情况
     * @param a a
     * @param b b
     * @return 返回 a 除以 b 的结果
     */
    public static int divide(int a, int b) {
        // 在数学中，我们都知道，a / b 情况下，b是不可能为0的，
        // 否则都是不合法的结果
        return a / b; // 当b为0，就会抛出ArithmeticException异常
    }
}
