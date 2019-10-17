package manager;

import model.MilkProduct;
import model.Subscription;

public class DefaultPricingStrategy implements PricingStrategy {

    @Override
    public Double getPrice(MilkProduct milkProduct, Subscription subscription) {
        return milkProduct.getIndividualPacketPrice() * subscription.getSubscriptionType().getNumberOfDays();
    }
}
