package ua.edu.ucu.flowerstore.paydelivery;

import java.util.List;

public class Order {
    private List<Item> items;
    private Payment payment;
    private Delivery delivery;

    public Order(List<Item> items) {
        this.items = items;
    }

    public void setPayment(Payment paymentMethod) {
        this.payment = paymentMethod;
    }

    public void setDelivery(Delivery deliveryMethod) {
        this.delivery = deliveryMethod;
    }

    public double calculateTotalPrice() {
        double total = items.stream().mapToDouble(Item::getPrice).sum();
        if (delivery != null) {
            total += delivery.calculateDeliveryCost(total);
        }
        return total;
    }
}
