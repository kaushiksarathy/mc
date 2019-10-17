package manager;

import model.MilkProduct;
import model.Subscription;

public interface PricingStrategy {
    Double getPrice(MilkProduct milkProduct, Subscription subscription);
}
