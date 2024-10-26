package ua.edu.ucu.flowerstore.paydelivery;

import java.util.List;

public interface Delivery {
    void deliver(List<Item> items);
    double calculateDeliveryCost(double totalOrderCost);
}
