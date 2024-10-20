package ua.edu.ucu.flower_store.pay_delivery;

import java.util.List;

public class Order{
    private List<Item> items;
    private Payment payment;
    private Delivery delivery;

    public Order(List<Item> items) {
        this.items = items;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
    }

    public double TotalPrice() {
        double total = items.stream().mapToDouble(Item::getPrice).sum();
        if (delivery != null) {
            total += delivery.calculateDeliveryCost(total);
        }
        return total;
    }
}
