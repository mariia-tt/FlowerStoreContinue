package ua.edu.ucu.flowerstore.paydelivery;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PostDeliveryStrategyTest {
    
    private static final Double EXPECTED = 2.0;
    private static final Double DELTA = 0.01;
    private static final int PRICE_ONE = 200;
    private static final int PRICE_TWO = 30;
    private static final int PRICE_WITH_DELIVERY = 200;
    private PostDeliveryStrategy deliveryStrategy;

    @Before
    public void setUp() {
        deliveryStrategy = new PostDeliveryStrategy();
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
