package ua.edu.ucu.flowerstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.edu.ucu.flowerstore.flower.Flower;
import ua.edu.ucu.flowerstore.flower.FlowerColor;
import ua.edu.ucu.flowerstore.flower.FlowerType;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/flowers")
public class FlowerController {
    private static final Double SEPARAL_LENGTH_ONE = 5.5;
    private static final Double PRICE_ONE = 10.0;
    private static final Double SEPARAL_LENGTH_TWO = 6.0;
    private static final Double PRICE_TWO = 7.5;

    @GetMapping("/flower")
    public List<Flower> getFlowers() {
        return Arrays.asList(
            new Flower(SEPARAL_LENGTH_ONE, FlowerColor.RED, PRICE_ONE,
             FlowerType.ROSE),
            new Flower(SEPARAL_LENGTH_TWO, FlowerColor.YELLOW, PRICE_TWO,
             FlowerType.TULIP)
        );
    }
}
