package com.palehorsestudios.alone.Foods;

import junit.framework.TestCase;

public class FoodFactoryTest extends TestCase {

    public void testGetNewInstance() {
        Food food = FoodFactory.getNewInstance("Fish");
        Food realFood = new Food("Fish", 0.84, 3401.94);

        assertEquals(realFood, food);
    }
}