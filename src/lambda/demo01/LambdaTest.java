package lambda.demo01;

/**
 * Lambda表达式初体验
 *  代码更简洁
 *  函数式编程：更关注函数/功能，而非对象
 *      函数是“第一等公民”
 *      可以赋值给变量
 *      可以作为（其它函数的）参数进行传递
 *      可以作为（其它函数的）返回值
 */
public class LambdaTest {

    public static void main(String[] args) {
        // 1. 子类实现接口
        Factory factory1 = new SubFactory();
        User user1 = (User)factory1.getObject();
        System.out.println(user1);

        // 2. 匿名内部类
        Factory factory2 = new Factory() {
            @Override
            public Object getObject() {
                return new User("zhangsan", 39);
            }
        };
        User user2 = (User) factory2.getObject();
        System.out.println(user2);

        // 3. lambda表达式
        Factory factory3 = () -> {
            return new User("lisi", 18);
        };
        User user3 = (User) factory3.getObject();
        System.out.println(user3);

        Factory factory4 = () -> new User("wanwu", 20);
        User user4 = (User) factory4.getObject();
        System.out.println(user4);


        // 4. lambda表达式作为参数传递和返回值
        User user5 = getUserFromFactory(() -> new User("zhaoliu", 19), "User");
        System.out.println(user5);

        Factory factory = getFactory();
        System.out.println(factory.getObject());
    }

    public static User getUserFromFactory(Factory factory, String beanName) {
        Object obj = factory.getObject();
        if (obj != null && obj.getClass().getSimpleName().equals(beanName)) {
            return (User)obj;
        }
        return null;
    }

    public static Factory getFactory() {
        return () -> {return new User("qianqi", 18);};
    }

}
