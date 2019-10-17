package dao;

import model.MilkProduct;
import model.Subscription;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static model.SubscriptionType.MONTHLY;
import static model.SubscriptionType.QUARTERLY;
import static model.SubscriptionType.YEARLY;

public class SubscriptionDAO {
    private Map<String, List<Subscription>> subscriptionMap = new HashMap<>();

    public SubscriptionDAO(MilkProductDAO milkProductDAO) {
        if (subscriptionMap == null) {
            subscriptionMap = new HashMap<>();
        }
        List<MilkProduct> milkProducts = milkProductDAO.getMilkProducts(null);
        for (MilkProduct milkProduct : milkProducts) {
            String id = milkProduct.getId();
            Subscription qSubscription = new Subscription(QUARTERLY, id, milkProduct.getIndividualPacketPrice() * QUARTERLY.getNumberOfDays());
            Subscription mSubscription = new Subscription(MONTHLY, id, milkProduct.getIndividualPacketPrice() * MONTHLY.getNumberOfDays());
            List<Subscription> subscriptions = new ArrayList<>();
            subscriptions.add(qSubscription);
            subscriptions.add(mSubscription);
            subscriptionMap.put(id, subscriptions);
            if (Integer.valueOf(id) % 3 == 0) {
                Subscription ySubscription = new Subscription(YEARLY, id, milkProduct.getIndividualPacketPrice() * YEARLY.getNumberOfDays());
                subscriptionMap.getOrDefault(id, new ArrayList<>()).add(ySubscription);
            }
        }
    }

    public List<Subscription> getSubscription(String milkProductId) {
        return subscriptionMap.getOrDefault(milkProductId, Collections.emptyList());
    }

    public List<Subscription> getAllSubscription() {
        return subscriptionMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }
}
