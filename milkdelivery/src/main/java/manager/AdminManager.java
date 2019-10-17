package manager;

import dao.OrdersDAO;
import model.Order;
import model.SubscriptionOrder;
import model.SubscriptionType;

import java.util.Date;
import java.util.List;

public class AdminManager {
    private OrdersDAO ordersDAO;

    public AdminManager(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }


    public List<SubscriptionOrder> getSubscriptions(SubscriptionType subscriptionType) {
        return ordersDAO.getSubscriptionOrders(subscriptionType);
    }
}
