package com.palehorsestudios.alone.activity;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class DrinkWaterActivityTest {
    Logger logger = Logger.getLogger(DrinkWaterActivityTest.class.getName());
    Activity drinkWater;
    Activity getWater;
    Player player;

    @Before
    public void setUp() {
        drinkWater = DrinkWaterActivity.getInstance();
        getWater = GetWaterActivity.getInstance();
        player = new Player(ItemFactory.getNewInstances(
                "Axe",
                "Knife",
                "Fishing Line",
                "Fishing Hooks",
                "Wire",
                "Harmonica",
                "Flint and Steel",
                "Pot",
                "First Aid Kit",
                "Cold Weather Gear"
        ));
        player.getShelter().addFoodToCache(FoodFactory.getNewInstance("Fish"), 1000);
        player.getShelter().addFoodToCache(FoodFactory.getNewInstance("Squirrel"), 1000);
        player.getShelter().addFoodToCache(FoodFactory.getNewInstance("Rabbit"), 1000);
        player.getShelter().addFoodToCache(FoodFactory.getNewInstance("Porcupine"), 1000);
        player.getShelter().addFoodToCache(FoodFactory.getNewInstance("Moose"), 1000);
    }

    @Test
    public void testDrinkWaterHappyOneWater() {
        player.getShelter().updateWater(-100);
        player.getShelter().updateWater(1);
        int previousHydration = player.getHydration();
        int previousWaterTank = player.getShelter().getWaterTank();
        assertEquals("That's better. Your hydration is now at "
                + (previousHydration + 1)
                + ", and you have "
                + (previousWaterTank - 1)
                + " water(s) remaining.", drinkWater.act(new Choice("drink", player)));
    }

    @Test
    public void testDrinkWaterHappyTwoWaters() {
        player.getShelter().updateWater(-100);
        player.getShelter().updateWater(2);
        int previousHydration = player.getHydration();
        int previousWaterTank = player.getShelter().getWaterTank();
        assertEquals("That's better. Your hydration is now at "
                + (previousHydration + 2)
                + ", and you have "
                + (previousWaterTank - 2)
                + " water(s) remaining.", drinkWater.act(new Choice("drink", player)));
    }

    @Test
    public void testDrinkWaterHappyThreeWaters() {
        while (player.getShelter().getWaterTank() < 3) {
            GetWaterActivity.getInstance().act(new Choice("water", player));
        }
        int previousHydration = player.getHydration();
        int previousWaterTank = player.getShelter().getWaterTank();
        assertEquals("That's better. Your hydration is now at "
                + (previousHydration + 3)
                + ", and you have "
                + (previousWaterTank - 3)
                + " water(s) remaining.", drinkWater.act(new Choice("drink", player)));
    }

    @Test
    public void testDrinkWaterFail() {
        assertEquals(
                "There isn't a drop left in your water tank. You should go fetch some water soon before you die of thirst!",
                DrinkWaterActivity.getInstance().act(new Choice("drink", player)));
        assertEquals(0, player.getShelter().getWaterTank());
    }
}