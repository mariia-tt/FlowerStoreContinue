package ua.edu.ucu.flower_store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import org.junit.jupiter.api.Assertions;

public class FlowerTest {
    private static final Random RANDOM_GENERATOR = new Random();
    private static final int MAX_PRICE = 100;
    private static final double DEFAULT_SEPAL_LENGTH = 5.5;
    private Flower flower;

    @BeforeEach
    public void init() {
        double price = RANDOM_GENERATOR.nextDouble() * MAX_PRICE;
        FlowerColor color = FlowerColor.RED;
        double sepalLength = DEFAULT_SEPAL_LENGTH;
        FlowerType type = FlowerType.ROSE;
        flower = new Flower(price, color, sepalLength, type);
    }

    @Test
    public void testPrice() {
        int price = RANDOM_GENERATOR.nextInt(MAX_PRICE);
        flower.setPrice(price);
        Assertions.assertEquals(price, flower.getPrice());
    }

    @Test
    public void testColor() {
        FlowerColor color = FlowerColor.RED;
        flower.setColor(color);
        Assertions.assertEquals("#FF0000", flower.getColor().toString());
    }
}