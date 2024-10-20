package ua.edu.ucu.flower_store.pay_delivery;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DHLDeliveryStrategyTest {
    private DHLDeliveryStrategy deliveryStrategy;

    @Before
    public void setUp() {
        deliveryStrategy = new DHLDeliveryStrategy();
    }

    @Test
    public void testDeliveryCost() {
        List<Item> items = new ArrayList<>();
        items.add(new MockItem(100));
        double cost = deliveryStrategy.calculateDeliveryCost(100);
        assertEquals(2.0, cost, 0.01);
    }

    @Test
    public void testDeliver() {
        List<Item> items = new ArrayList<>();
        items.add(new MockItem(50));
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
