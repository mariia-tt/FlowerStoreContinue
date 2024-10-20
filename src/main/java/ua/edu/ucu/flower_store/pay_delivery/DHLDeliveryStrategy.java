package ua.edu.ucu.flower_store.pay_delivery;

import java.util.List;

public class DHLDeliveryStrategy implements Delivery {
    private static final double POST_DHL_PERCENT = 0.02;

    @Override
    public void deliver(List<Item> items) {
        System.out.println("Delivering via DHL: " + items);
    }

    @Override
    public double calculateDeliveryCost(double priceWithDelivery) {
        return priceWithDelivery * POST_DHL_PERCENT;
    }
}
