package model;

import lombok.ToString;

@ToString
public class MilkProduct {
    private String id;
    private String name;
    private String brand;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public Double getIndividualPacketPrice() {
        return individualPacketPrice;
    }

    private Double individualPacketPrice;

    public MilkProduct(String id, String name, String brand, Double individualPacketPrice) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.individualPacketPrice = individualPacketPrice;
    }
}
