package ua.edu.ucu.flowerstore;

import ua.edu.ucu.flowerstore.flower.FlowerBucket;
import ua.edu.ucu.flowerstore.flower.FlowerColor;
import ua.edu.ucu.flowerstore.flower.FlowerPack;
import ua.edu.ucu.flowerstore.flower.FlowerType;

public class Store {
    private FlowerBucket flowers;

    public Store() {
        this.flowers = new FlowerBucket();
    }

    public void addFlowerPack(FlowerPack flowerPack) {
        flowers.add(flowerPack);
    }

    public FlowerBucket search(FlowerType type, FlowerColor color,
     Double minPrice,
     Double maxPrice) {
        FlowerBucket result =  new FlowerBucket();
        for (FlowerPack packet : flowers.getFlowerPacks()) {
            if (matchesType(packet, type) && matchesColor(packet,
             color) && matchesPrice(packet, minPrice, maxPrice)) {
                result.add(packet);
            }
        }
        return result;
    }

    private boolean matchesType(FlowerPack pack, FlowerType type) {
        return type == null || pack.getFlower().getFlowerType() == type;
    }
    
    private boolean matchesColor(FlowerPack pack, FlowerColor color) {
        return color == null || pack.getFlower().getColor() == color;
    }
    
    private boolean matchesPrice(FlowerPack pack, Double minPrice,
     Double maxPrice) {
        return (minPrice == null || pack.getPrice() >= minPrice)
         && (maxPrice == null || pack.getPrice() <= maxPrice);
    }
    
}
