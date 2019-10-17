package manager;

import dao.OrdersDAO;
import model.Order;
import model.SubscriptionType;

import java.util.Date;
import java.util.List;

public class AdminManager {
    private OrdersDAO ordersDAO;

    public AdminManager(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    public List<Order> getOnDemandOrder(Date date) {
        return ordersDAO.getOndemandOrders(date);
    }

    public List<Order> getSubscriptions(SubscriptionType subscriptionType) {
        return ordersDAO.getSubscriptionOrders(subscriptionType);
    }
}
