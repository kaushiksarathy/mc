package dao;

import model.OndemandOrder;
import model.Order;
import model.SubscriptionOrder;
import model.SubscriptionType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersDAO {
    List<Order> orders = new ArrayList<>();

    public void addOndemandOrder(String customerId, String milkProductId, int quantity, Date date) {
        orders.add(new OndemandOrder(customerId, milkProductId, quantity, date));
    }

    public void addSubscriptionOrder(String customerId, String milkProductId, SubscriptionType subscriptionType) {
        orders.add(new SubscriptionOrder(customerId, milkProductId, subscriptionType));
    }

    public List<Order> getOndemandOrders(Date date) {
        return orders.stream().filter(order -> order.getOrderType() == Order.OrderType.ONDEMAND)
                .filter(order -> ((OndemandOrder)order).getDate().compareTo(date) == 0).collect(Collectors.toList());
    }

    public List<Order> getSubscriptionOrders(SubscriptionType subscriptionType) {
        return orders.stream().filter(order -> order.getOrderType() == Order.OrderType.SUBSCRIPTION)
                .filter(order -> ((SubscriptionOrder)order).getSubscriptionType() == subscriptionType).collect(Collectors.toList());
    }

    public List<Order> getOrdersForUser(String customerId) {
        return orders.stream().filter(order -> order.getCustomerId().equals(customerId)).collect(Collectors.toList());
    }
}
