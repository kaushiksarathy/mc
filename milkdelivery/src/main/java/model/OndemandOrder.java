package model;

import java.util.Date;

public class OndemandOrder extends Order {
    private String milkProductId;
    private int quantity;
    private Date date;

    public String getMilkProductId() {
        return milkProductId;
    }

    public Date getDate() {
        return date;
    }

    public OndemandOrder(String customerId, String milkProductId, int quantity, Date date) {
        super(OrderType.ONDEMAND, customerId);
        this.milkProductId = milkProductId;
        this.quantity = quantity;
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("order by customer : %s for milk product with Id : %s for quantity : %s on date %s ", getCustomerId(), milkProductId, quantity, date);
    }
}
