package ua.edu.ucu.flower_store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Random;

public class FlowerBucketTest {
    private static final Random RANDOM_GENERATOR = new Random();
    private static final int MAX_QUANTITY = 1000;
    private static final int MAX_PRICE = 100;
    private static final int TEST_PRICE = 10;
    private static final double MAX_SEPAL_LENGTH = 5.5;

    private FlowerBucket flowerBucket;

    @BeforeEach
    public void init() {
        flowerBucket = new FlowerBucket();
    }

    @Test
    public void testPrice() {
        int price = RANDOM_GENERATOR.nextInt(MAX_PRICE);
        int quantity = RANDOM_GENERATOR.nextInt(MAX_QUANTITY);
        double sepalLength = (MAX_SEPAL_LENGTH);
        FlowerType type = FlowerType.ROSE;

        Flower flower = new Flower(sepalLength, FlowerColor.RED, price, type);
        flower.setPrice(TEST_PRICE);
        FlowerPack flowerPack = new FlowerPack(flower, quantity);
        flowerBucket.add(flowerPack);
        Assertions.assertEquals(TEST_PRICE * quantity, flowerBucket.getPrice());
    }
}
