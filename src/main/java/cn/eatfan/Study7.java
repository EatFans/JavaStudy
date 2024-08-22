package cn.eatfan;

/**
 * Java 枚举
 */
public class Study7 {
    /**
     * 枚举定义：
     *  枚举是一个特殊的类，一般表示一组常量，比如一年的 4 个季节，一年的 12 个月份，一个星期的 7 天，方向有东南西北等。
     * Java 枚举类使用 enum 关键字来定义，各个常量使用逗号 , 来分割。
     *
     *  下面会展示两个枚举实例，一个简单枚举使用方法，另一个是更详细一些的枚举使用方法。
     *  Day 枚举类展示的是醉简单的枚举使用方法，
     *  PizzaSize 枚举类展示的是更详细一些的枚举使用方法。
     */
    public static void main(String[] args){
        // 将Day枚举类中的MONDAY设置为一个Day类型的变量today存储
        Day today = Day.MONDAY;
        // 这里输出today
        System.out.println("Today is "+today);

        // 使用switch语法来判断Day
        switch(today){
            case MONDAY:
                System.out.println("今天是星期一，是工作日");
                break;
            case WEDNESDAY:
                System.out.println("今天是星期三，是工作日");
                break;
            case SUNDAY:
                System.out.println("今天是星期天，是休息日");
                break;
            default:
                break;
        }

        // 枚举类可以通过for循环遍历所有的值
        System.out.println("遍历Day枚举的所有的值：");
        for (Day day : Day.values()){
            System.out.println(day);
        }

        System.out.println(" ");

        // 实例二，关于Java枚举更详细的讲解用法
        // 下面会通过来枚举披萨尺寸大小的例子，来详细讲解枚举的用法。
        // 这里定义一个大号披萨尺寸的枚举值变量
        PizzaSize size = PizzaSize.LARGE;

        // 输出披萨尺寸枚举值
        System.out.println("披萨尺寸: " + size);
        // 使用PizzaSize 枚举类中的方法get方法来获取该尺寸的直径和价格
        System.out.println("直径: " + size.getDiameter());
        System.out.println("价格: $" + size.getPrice());

        // 调用枚举中定义的抽象方法
        System.out.println("披萨尺寸的介绍: " + size.getDescription());
    }

}

/**
 * 通过使用 enum 关键词来定义个枚举 Day 类，
 * 下面是一个简单的枚举实例：
 */
enum Day {
    /**
     * 下面这些都是 Day 枚举出来的，从星期一到星期天
     */
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

/**
 * 通过使用 enum 关键词来定义个枚举 PizzaSize 类
 */
enum PizzaSize {
    /**
     * 这是披萨尺寸小号的枚举，（）中是构造方法用来初始化PizzaSize枚举类的属性值
     */
    SMALL(8, 5.99) {
        /**
         * 这是PizzaSize枚举类中有个抽象方法，在这里通过注解@Overried，来实现本枚举的对应方法
         * @return 返回介绍字符串
         */
        @Override
        public String getDescription() {
            return "小号披萨适合一个人。";
        }
    },
    /**
     * 这是披萨尺寸中号的枚举，同上
     */
    MEDIUM(12, 8.99) {
        @Override
        public String getDescription() {
            return "中号披萨合适两个人。";
        }
    },
    /**
     * 这是披萨尺寸大号的枚举，同上
     */
    LARGE(16, 12.99) {
        @Override
        public String getDescription() {
            return "大号披萨适合三到四个人。";
        }
    };

    /**
     * 下面属性是每个枚举具体的属性和数值，通过下面PizzaSize()构造方法来初始化值
     */
    private final int diameter; // 披萨直径
    private final double price; // 披萨价格

    /**
     * PizzaSize枚举类的构造方法，本构造方法是用来初始化本类中的两个属性diameter和price的值
     * @param diameter 披萨直径
     * @param price 披萨价格
     */
    PizzaSize(int diameter, double price) {
        this.diameter = diameter;
        this.price = price;
    }

    /**
     * 这是 PizzaSize 枚举类中的get方法，用来获取披萨直径
     * @return 返回披萨直径
     */
    public int getDiameter() {
        return diameter;
    }

    /**
     * 这是 PizzaSize 枚举类中的get方法，用来获取披萨价格
     * @return 返回披萨价格
     */
    public double getPrice() {
        return price;
    }

    /**
     * 这里定义了一个抽象的方法getDescription()，这个抽象方法只是声明定义了，
     * 并没有实际实现代码，具体的实现，需要在枚举中重写实现，在枚举中通过使用注解@Override，
     * 来重写实现下面方法。
     * 该方法含义为：获取介绍
     * @return 返回介绍字符串
     */
    public abstract String getDescription();
}