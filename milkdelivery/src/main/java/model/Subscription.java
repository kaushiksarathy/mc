package model;

import lombok.ToString;

@ToString
public class Subscription {
    private SubscriptionType subscriptionType;
    private String milkProductId;
    private Double price;

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public String getMilkProductId() {
        return milkProductId;
    }

    public Double getPrice() {
        return price;
    }



    public Subscription(SubscriptionType subscriptionType, String milkProductId, Double price) {
        this.subscriptionType = subscriptionType;
        this.milkProductId = milkProductId;
        this.price = price;
    }


}
