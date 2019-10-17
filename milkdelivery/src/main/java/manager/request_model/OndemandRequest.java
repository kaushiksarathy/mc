package manager.request_model;

import java.util.Date;

public class OndemandRequest {
    private String customerId;
    private String milkProductId;
    private int quantity;
    private Date date;

    public OndemandRequest(String customerId, String milkProductId, int quantity, Date date) {
        this.customerId = customerId;
        this.milkProductId = milkProductId;
        this.quantity = quantity;
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getMilkProductId() {
        return milkProductId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return date;
    }
}
