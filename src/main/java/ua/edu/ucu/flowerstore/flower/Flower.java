package ua.edu.ucu.flowerstore.flower;

import lombok.Getter;
import lombok.Setter;

@Setter
public class Flower {
    @Getter
    private double sepalLength;
    private FlowerColor color;
    private double price;
    @Getter
    private FlowerType flowerType;

    public Flower(double sepalLength, FlowerColor color,
     double price, FlowerType flowerType) {
        this.sepalLength = sepalLength;
        this.color = color;
        this.price = price;
        this.flowerType = flowerType;
    }

    public void setPrice(double newprice) {
        this.price = newprice;
    }

    public void setColor(FlowerColor newcolor) {
        this.color = newcolor;
    }

    public Double getPrice() {
        return price;
    }

    public FlowerType getFlowerType() {
        return this.flowerType;
    }

    public FlowerColor getColor() {
        return this.color;
    }
}