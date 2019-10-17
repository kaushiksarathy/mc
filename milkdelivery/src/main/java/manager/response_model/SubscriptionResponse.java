package manager.response_model;

import lombok.Getter;
import lombok.ToString;
import model.SubscriptionType;

@ToString
@Getter
public class SubscriptionResponse {
    private String milkProductName;
    private SubscriptionType subscriptionType;
    private Double price;

    public SubscriptionResponse( String milkProductName, SubscriptionType subscriptionType, Double price) {
        this.subscriptionType = subscriptionType;
        this.milkProductName = milkProductName;
        this.price = price;
    }
}
