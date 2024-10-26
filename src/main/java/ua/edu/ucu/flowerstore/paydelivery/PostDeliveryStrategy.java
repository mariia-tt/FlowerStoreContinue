package ua.edu.ucu.flowerstore.paydelivery;

import java.util.List;

public class PostDeliveryStrategy implements Delivery {
    private static final double POST_DELIVERY_PERCENT = 0.01;

    @Override
    public void deliver(List<Item> items) {
        System.out.println("Delivering via Post: " + items);
    }

    @Override
    public double calculateDeliveryCost(double priceWithDelivery) {
        return priceWithDelivery * POST_DELIVERY_PERCENT;
    }
}
