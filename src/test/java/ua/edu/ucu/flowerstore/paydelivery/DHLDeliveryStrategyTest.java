package ua.edu.ucu.flowerstore.paydelivery;

import org.junit.Before;
import org.junit.Test;

import ua.edu.ucu.flowerstore.paydelivery.Item;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DHLDeliveryStrategyTest {
    private DHLDeliveryStrategy deliveryStrategy;
    private static final int priceOne = 100;
    private static final int priceWithDelivery = 100;
    private static final double expected = 2.0;
    private static final double delta = 0.01;
    private static final int priceTwo = 50;

    @Before
    public void setUp() {
        deliveryStrategy = new DHLDeliveryStrategy();
    }

    @Test
    public void testDeliveryCost() {
        List<Item> items = new ArrayList<>();
        items.add(new MockItem(priceOne));
        double cost = deliveryStrategy.calculateDeliveryCost
        (priceWithDelivery);
        assertEquals(expected, cost, delta);
    }

    @Test
    public void testDeliver() {
        List<Item> items = new ArrayList<>();
        items.add(new MockItem(priceTwo));
        deliveryStrategy.deliver(items);
    }

    private class MockItem implements Item {
        private double price;

        MockItem(double price) {
            this.price = price;
        }

        @Override
        public double getPrice() {
            return price;
        }
    }
}
