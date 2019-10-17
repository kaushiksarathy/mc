package manager.request_model;

import model.SubscriptionType;

public class SubscriptionRequest {
    private String customerId;
    private String milkProductId;
    private SubscriptionType type;

    public SubscriptionRequest(String customerId, String milkProductId, SubscriptionType type) {
        this.customerId = customerId;
        this.milkProductId = milkProductId;
        this.type = type;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getMilkProductId() {
        return milkProductId;
    }

    public SubscriptionType getType() {
        return type;
    }
}
