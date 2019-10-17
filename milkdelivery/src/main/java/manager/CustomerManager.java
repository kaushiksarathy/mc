package manager;

import dao.MilkProductDAO;
import dao.OrdersDAO;
import dao.SubscriptionDAO;
import manager.request_model.SubscriptionRequest;
import manager.response_model.SubscriptionResponse;
import model.MilkProduct;
import model.Order;
import model.Subscription;
import model.SubscriptionOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerManager {

    private MilkProductDAO milkProductDAO;
    private OrdersDAO ordersDAO;
    private SubscriptionDAO subscriptionDAO;
    private PricingStrategy pricingStrategy;
    private final Map<String, AtomicInteger> milkLatchMap = new ConcurrentHashMap<>();
    private int threashold;
    private final static String SUBSCRIPTION_RESPONSE = "Your order for milk will be delivered between 5 to 7 AM everyday";

    public CustomerManager(MilkProductDAO milkProductDAO, OrdersDAO ordersDAO, SubscriptionDAO subscriptionDAO, PricingStrategy pricingStrategy, int threashold) {
        this.milkProductDAO = milkProductDAO;
        this.ordersDAO = ordersDAO;
        this.subscriptionDAO = subscriptionDAO;
        this.pricingStrategy = pricingStrategy;
        this.threashold = threashold;

        subscriptionDAO.getAllSubscription().forEach(subscription -> milkLatchMap.put(subscription.getMilkProductId(), new AtomicInteger(0)));
    }

    public List<MilkProduct> getAllMilkProducts(String sortBy) {
        return milkProductDAO.getMilkProducts(sortBy);
    }

    public List<SubscriptionResponse> getAllSubscriptions() {
        List<SubscriptionResponse> responses = new ArrayList<>();
        for(Subscription subscription : subscriptionDAO.getAllSubscription()) {
            MilkProduct milkProduct = milkProductDAO.getMilkProduct(subscription.getMilkProductId());
            Double price = pricingStrategy.getPrice(milkProduct, subscription);
            responses.add(new SubscriptionResponse(milkProduct.getName()+"-"+milkProduct.getBrand(), subscription.getSubscriptionType(), price));
        }
        return responses;
    }


    public String subscribe(SubscriptionRequest subscriptionRequest) throws Exception {
        AtomicInteger atomicInteger = milkLatchMap.get(subscriptionRequest.getMilkProductId());
        if(atomicInteger == null)
            throw new Exception("Sorry for inconvience we are not taking orders for "+subscriptionRequest.getMilkProductId());
        int subscriptionOrdersCount = atomicInteger.incrementAndGet();
        if(subscriptionOrdersCount > threashold) {
            throw new Exception("Sorry for inconvience we are not taking orders more than "+threashold);
        }
        ordersDAO.addSubscriptionOrder(subscriptionRequest.getCustomerId(), subscriptionRequest.getMilkProductId(), subscriptionRequest.getType());

        return SUBSCRIPTION_RESPONSE;
    }

    public List<SubscriptionOrder> getAllOrders(String customerId) {
        return ordersDAO.getOrdersForUser(customerId);
    }
}

