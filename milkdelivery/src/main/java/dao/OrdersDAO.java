package dao;

import model.SubscriptionOrder;
import model.SubscriptionType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrdersDAO {
    private Map<String, List<SubscriptionOrder>> subscriptionOrdersForMilkProduct = new HashMap<>();


    public void addSubscriptionOrder(String customerId, String milkProductId, SubscriptionType subscriptionType) {
        SubscriptionOrder subscriptionOrder = new SubscriptionOrder(customerId, milkProductId, subscriptionType);
        subscriptionOrdersForMilkProduct.computeIfAbsent(milkProductId, key -> new ArrayList<>()).add(subscriptionOrder);
    }


    public List<SubscriptionOrder> getSubscriptionOrders(SubscriptionType subscriptionType) {
        return subscriptionOrdersForMilkProduct.values().stream().flatMap(Collection::stream)
                .filter(order -> order.getSubscriptionType() == subscriptionType).collect(Collectors.toList());
    }

    public List<SubscriptionOrder> getOrdersForUser(String customerId) {
        return subscriptionOrdersForMilkProduct.values().stream().flatMap(Collection::stream)
                .filter(order -> order.getCustomerId().equals(customerId)).collect(Collectors.toList());
    }
}
