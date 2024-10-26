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
    private Double SEPARALLENGHTONE = 5.5;
    private Double PRICEONE = 10.0;
    private Double SEPARALLENGHTTWO = 6.0;
    private Double PRICETWO = 7.5;

    @GetMapping
    public List<Flower> getFlowers() {
        return Arrays.asList(
            new Flower(SEPARALLENGHTONE, FlowerColor.RED, PRICEONE,
             FlowerType.ROSE),
            new Flower(SEPARALLENGHTTWO, FlowerColor.YELLOW, PRICETWO,
             FlowerType.TULIP)
        );
    }
}
