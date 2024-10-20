package ua.edu.ucu.flower_store.pay_delivery;

import java.util.List;

public interface Delivery {
    void deliver(List<Item> items);
    double calculateDeliveryCost(double totalOrderCost);
}
