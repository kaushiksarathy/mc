package model;

public class Order {
    private OrderType orderType;
    private String customerId;

    public OrderType getOrderType() {
        return orderType;
    }

    public String getCustomerId() {
        return customerId;
    }


    public Order(OrderType orderType, String customerId) {
        this.orderType = orderType;
        this.customerId = customerId;
    }

    public enum OrderType {
        ONDEMAND, SUBSCRIPTION
    }
}
