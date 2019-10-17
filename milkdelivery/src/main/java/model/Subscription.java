package model;

import lombok.ToString;

@ToString
public class Subscription {
    private SubscriptionType subscriptionType;
    private String milkProductId;

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public String getMilkProductId() {
        return milkProductId;
    }


    public Subscription(SubscriptionType subscriptionType, String milkProductId) {
        this.subscriptionType = subscriptionType;
        this.milkProductId = milkProductId;
    }


}
