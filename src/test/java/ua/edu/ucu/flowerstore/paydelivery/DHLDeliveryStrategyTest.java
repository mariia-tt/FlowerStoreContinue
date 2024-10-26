package ua.edu.ucu.flowerstore.paydelivery;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DHLDeliveryStrategyTest {

    private static final double DELTA = 0.01;
    private static final double EXPECTED = 2.0;
    private static final int PRICE_ONE = 100;
    private static final int PRICE_TWO = 50;
    private static final int PRICE_WITH_DELIVERY = 100;

    private DHLDeliveryStrategy deliveryStrategy;

    @Before
    public void setUp() {
        deliveryStrategy = new DHLDeliveryStrategy();
    }

    @Test
    public void testDeliveryCost() {
        List<Item> items = new ArrayList<>();
        items.add(new MockItem(PRICE_ONE));
        double cost = deliveryStrategy.calculateDeliveryCost(
            PRICE_WITH_DELIVERY);
        org.junit.Assert.assertEquals(EXPECTED, cost, DELTA);
    }

    @Test
    public void testDeliver() {
        List<Item> items = new ArrayList<>();
        items.add(new MockItem(PRICE_TWO));
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
