package com.palehorsestudios.alone;

import com.palehorsestudios.alone.Foods.Food;
import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.ItemFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class ShelterTest {

    Shelter shelter;
    Logger logger = Logger.getLogger(ShelterTest.class.getName());

    @Before
    public void setUp() {
        shelter = new Shelter();
    }

    @Test
    public void testAddFoodToCacheHappy() {
        shelter.addFoodToCache(FoodFactory.getNewInstance("Fish"), 1000);
        assertEquals(Optional.of(1000.0).get(), shelter.getFoodCache().get(FoodFactory.getNewInstance("Fish")));
    }

    @Test
    public void testRemoveFoodHappy() {
        shelter.addFoodToCache(FoodFactory.getNewInstance("Squirrel"), 1000.0);
        assertEquals(500.0, shelter.removeFoodFromCache(FoodFactory.getNewInstance("Squirrel"), 500.0), 0.001);
        assertEquals(500.0, shelter.getFoodCache().get(FoodFactory.getNewInstance("Squirrel")), 0.001);
    }

    @Test
    public void testRemoveFoodFail() {
        assertEquals(0.0, shelter.removeFoodFromCache(FoodFactory.getNewInstance("Rabbit"), 2000.0), 0.001);
    }

    @Test
    public void addEquipmentHappy() {
        shelter.addEquipment(ItemFactory.getNewInstance("Wire"), 1);
        assertEquals(1, shelter.getEquipment().get(ItemFactory.getNewInstance("Wire")), 0.0);
    }

    @Test
    public void removeEquipmentHappy() {
        shelter.addEquipment(ItemFactory.getNewInstance("Fishing Hooks"), 3);
        assertEquals(1, shelter.removeEquipment(ItemFactory.getNewInstance("Fishing Hooks"), 1));
        assertEquals(Optional.of(2).get(), shelter.getEquipment().get(ItemFactory.getNewInstance("Fishing Hooks")));
    }

    @Test
    public void removeEquipmentFail() {
        assertEquals(0, shelter.removeEquipment(ItemFactory.getNewInstance("Family Photo"), 500));
    }

    @Test
    public void testUpdateWaterAddHappy() {
        shelter.updateWater(1);
        assertEquals(1, shelter.getWaterTank());
    }

    @Test
    public void testUpdateWaterOverfill() {
        shelter.updateWater(20);
        assertEquals(10, shelter.getWaterTank());
    }

    @Test
    public void testUpdateWaterRemoveHappy() {
        shelter.updateWater(5);
        shelter.updateWater(-2);
        assertEquals(3, shelter.getWaterTank());
    }

    @Test
    public void testUpdateWaterRemoveFail() {
        shelter.updateWater(-20);
        assertEquals(0, shelter.getWaterTank());

    }

}
