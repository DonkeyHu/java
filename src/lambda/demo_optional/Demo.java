package lambda.demo_optional;

import java.util.Date;
import java.util.Optional;

/**
 * 链式调用更优雅解决空指针问题
 */
public class Demo {

    public static void main(String[] args) {
        User user = new User();
        Order order = new Order("dk1206");
        user.setOrder(order);
        String s = Optional.ofNullable(user).map(User::getOrder).map(Order::getOrderId).orElse("Other");
        System.out.println(s);


        if (user != null && user.getOrder() != null) {
            s =  user.getOrder().getOrderId();
        } else {
            s = "Other";
        }
    }


    public static class User {
        public String userId;
        public Order order;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }
    }

    public static class Order {
        public String orderId;
        public Date orderTime;

        public Order() {
        }

        public Order(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public Date getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(Date orderTime) {
            this.orderTime = orderTime;
        }
    }
}
