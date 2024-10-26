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
    private static final Double separalLenghtOne = 5.5;
    private static final Double priceOne = 10.0;
    private static final Double separalLenghtTwo = 6.0;
    private static final Double priceTwo = 7.5;

    @GetMapping("/flower")
    public List<Flower> getFlowers() {
        return Arrays.asList(
            new Flower(separalLenghtOne, FlowerColor.RED, priceOne,
             FlowerType.ROSE),
            new Flower(separalLenghtTwo, FlowerColor.YELLOW, priceTwo,
             FlowerType.TULIP)
        );
    }
}
