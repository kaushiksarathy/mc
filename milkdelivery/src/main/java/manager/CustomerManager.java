package manager;

import dao.MilkProductDAO;
import dao.OrdersDAO;
import dao.SubscriptionDAO;
import manager.request_model.OndemandRequest;
import manager.request_model.SubscriptionRequest;
import model.MilkProduct;
import model.Order;
import model.Subscription;

import java.util.List;

public class CustomerManager {

    private MilkProductDAO milkProductDAO;
    private OrdersDAO ordersDAO;
    private SubscriptionDAO subscriptionDAO;
    private final static String ON_DEMAND_RESPONSE = "Your order for milk will be delivered on % between 5 to 7 PM";
    private final static String SUBSCRIPTION_RESPONSE = "Your order for milk will be delivered between 5 to 7 AM everyday";

    public CustomerManager(MilkProductDAO milkProductDAO, OrdersDAO ordersDAO, SubscriptionDAO subscriptionDAO) {
        this.milkProductDAO = milkProductDAO;
        this.ordersDAO = ordersDAO;
        this.subscriptionDAO = subscriptionDAO;
    }

    public List<MilkProduct> getAllMilkProducts(String sortBy) {
        return milkProductDAO.getMilkProducts(sortBy);
    }

    public List<Subscription> getAllSubscriptionsForMilkProduct(String milkProductId) {
        return subscriptionDAO.getSubscription(milkProductId);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionDAO.getAllSubscription();
    }

    public List<Order> getAllOrders(String customerId) {
        return ordersDAO.getOrdersForUser(customerId);
    }

    public String orderOnDemand(OndemandRequest ondemandRequest) {
        ordersDAO.addOndemandOrder(ondemandRequest.getCustomerId(), ondemandRequest.getMilkProductId(), ondemandRequest.getQuantity(), ondemandRequest.getDate());
        return String.format(ON_DEMAND_RESPONSE, ondemandRequest.getDate());
    }

    public String subscribe(SubscriptionRequest subscriptionRequest) {
        ordersDAO.addSubscriptionOrder(subscriptionRequest.getCustomerId(), subscriptionRequest.getMilkProductId(), subscriptionRequest.getType());
        return SUBSCRIPTION_RESPONSE;
    }
}
