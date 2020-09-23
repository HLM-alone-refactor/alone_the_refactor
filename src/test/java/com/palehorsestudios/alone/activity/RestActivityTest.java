package com.palehorsestudios.alone.activity;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.player.Player;
import com.palehorsestudios.alone.player.SuccessRate;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class RestActivityTest {

    static final double LOW_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT = 179.8;
    static final double LOW_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT = 179.7;

    Logger logger = Logger.getLogger(RestActivityTest.class.getName());
    Activity rest;
    Player player;

    @Before
    public void setUp() {
        rest = RestActivity.getInstance();
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
    public void testRest() {
        int previousHydration = player.getHydration();
        String restResult = rest.act(new Choice("rest", player));
        String[] restResultWords = restResult.split(" ");
        int hours = Integer.parseInt(restResultWords[3]);
        assertEquals("You rested for " + hours + " hours and are ready for the next adventure!", restResult);
        if (hours < 4) {
            assertEquals(LOW_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.LOW.getHydrationCost(SuccessRate.LOW), player.getHydration());
            assertEquals(6.0, player.getMorale(), 0.001);
        } else {
            assertEquals(LOW_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.LOW.getHydrationCost(SuccessRate.MEDIUM), player.getHydration());
            assertEquals(7.0, player.getMorale(), 0.001);
        }
    }
}