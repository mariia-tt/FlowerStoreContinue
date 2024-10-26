package ua.edu.ucu.flowerstore.flower;

import java.util.ArrayList;

public class FlowerBucket {
    private ArrayList<FlowerPack> flowerBucketList;

    public FlowerBucket() {
        this.flowerBucketList = new ArrayList<>();
    }

    public FlowerBucket(ArrayList<FlowerPack> flowerBucketList) {
        this.flowerBucketList = flowerBucketList;
    }

    public void add(FlowerPack flowerPack) {
        this.flowerBucketList.add(flowerPack);
    }

    public double getPrice() {
        double sum = 0;
        int l = flowerBucketList.size();
        for (int i = 0; i < l; i++) {
            FlowerPack pack = flowerBucketList.get(i);
            sum += pack.getPrice();
        }
        return sum;
    }

    public ArrayList<FlowerPack> getFlowerPacks() {
        return this.flowerBucketList;
    }

    public void addFlowerPack(FlowerPack pack) {
        throw new UnsupportedOperationException(
            "Unimplemented method 'addFlowerPack'");
    }
}
