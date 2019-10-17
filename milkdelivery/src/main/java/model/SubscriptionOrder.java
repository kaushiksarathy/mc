package model;

public class SubscriptionOrder extends Order {
    private String milkProductId;
    private SubscriptionType subscriptionType;

    public String getMilkProductId() {
        return milkProductId;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public SubscriptionOrder(String customerId,String milkProductId, SubscriptionType subscriptionType) {
        super(OrderType.SUBSCRIPTION, customerId);
        this.milkProductId = milkProductId;
        this.subscriptionType = subscriptionType;
    }

    @Override
    public String toString() {
        return String.format("order by customer : %s for milk product with Id : %s with subscription : %s ", getCustomerId(), milkProductId, subscriptionType);
    }
}
