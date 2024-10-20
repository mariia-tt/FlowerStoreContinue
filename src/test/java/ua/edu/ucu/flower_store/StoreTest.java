package ua.edu.ucu.flower_store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class StoreTest {

    private static final double PRICE_ONE = 5.0;
    private static final int QUANTITY_ONE = 10;
    private static final int QUANTITY_FIVE = 5;
    private static final double PRICE_TWO = 6.0;
    private static final double PRICE_THREE = 7.0;
    private static final int QUANTITY_TWO = 3;
    private static final double PRICE_FOUR = 4.0;
    private static final int QUANTITY_THREE = 7;
    private static final double LENGHT_ONE = 2.5;
    private static final double LENGHT_TWO = 2.5;
    private static final double LENGHT_TREE = 2.5;
    private static final double LENGHT_FOUR = 2.5;

    private Store store;

    @BeforeEach
    public void setUp() {
        store = new Store();
        store.addFlowerPack(new FlowerPack(new Chamomile(LENGHT_ONE,
         FlowerColor.YELLOW, PRICE_ONE), QUANTITY_ONE));
        store.addFlowerPack(new FlowerPack(new Rose(LENGHT_TWO,
         FlowerColor.RED, PRICE_TWO), QUANTITY_FIVE));
        store.addFlowerPack(new FlowerPack(new Tulip(LENGHT_TREE,
         FlowerColor.BLUE, PRICE_THREE), QUANTITY_TWO));
        store.addFlowerPack(new FlowerPack(new Chamomile(LENGHT_FOUR,
         FlowerColor.WHITE, PRICE_FOUR), QUANTITY_THREE));
    }


    @Test public void testSearchByType() {
       FlowerBucket results = store.search(FlowerType.CHAMOMILE,
         null, null, null);
        Assertions.assertEquals(2, results.getFlowerPacks().size());
    }

    @Test
    public void testSearchByColor() {
        FlowerBucket results = store.search(null,
         FlowerColor.RED, null, null);
        Assertions.assertEquals(1, results.getFlowerPacks().size());
    }



    @Test
    public void testSearchByAllCriteria() {
        FlowerBucket results = store.search(FlowerType.ROSE,
         FlowerColor.RED, null, null);
        Assertions.assertEquals(1, results.getFlowerPacks().size());
    }

    @Test
    public void testSearchNoResults() {
        FlowerBucket results = store.search(FlowerType.TULIP,
         FlowerColor.WHITE, null, null);
        Assertions.assertEquals(0, results.getFlowerPacks().size());
    }
}
