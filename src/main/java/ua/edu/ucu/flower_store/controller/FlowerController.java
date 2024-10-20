package ua.edu.ucu.flower_store.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.edu.ucu.flower_store.Flower;
import ua.edu.ucu.flower_store.FlowerColor;
import ua.edu.ucu.flower_store.FlowerType;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/flowers")
public class FlowerController {

    @GetMapping
    public List<Flower> getFlowers() {
        return Arrays.asList(
            new Flower(5.5, FlowerColor.RED, 10.0, FlowerType.ROSE),
            new Flower(6.0, FlowerColor.YELLOW, 7.5, FlowerType.TULIP)
        );
    }
}
