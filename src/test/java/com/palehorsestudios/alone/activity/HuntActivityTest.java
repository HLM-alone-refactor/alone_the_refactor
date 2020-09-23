package com.palehorsestudios.alone.activity;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Foods.Food;
import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.player.Player;
import com.palehorsestudios.alone.player.SuccessRate;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HuntActivityTest {

    static final double HIGH_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT = 178.5;
    static final double HIGH_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT = 177.1;
    static final double HIGH_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT = 174.5;

    Logger logger = Logger.getLogger(HuntActivityTest.class.getName());
    Activity getItemFromShelter;
    Activity goHunting;
    Player player;
    Food moose;
    Food porcupine;

    @Before
    public void setUp() {
        goHunting = HuntActivity.getInstance();
        getItemFromShelter = GetItemActivity.getInstance();
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
        
        moose = FoodFactory.getNewInstance("Moose");
        porcupine = FoodFactory.getNewInstance("Porcupine");
    }

    @Test
    public void testGoHuntingNoItems() {
        int previousHydration = player.getHydration();
        String huntingResult = goHunting.act(new Choice("go hunting", player));
        String[] possibleResults = new String[]{"I guess that's why they don't call it killing. You couldn't get a shot on an animal.",
                "Watch out for those quills! You killed a nice fat porcupine that should keep you fed for a while.",
                "Moose down! It took five trips, but you were able to process the meat and transport it back to your shelter before a predator got to it first."};
        boolean validResult = false;
        for (String possibleResult : possibleResults) {
            if (huntingResult.equals(possibleResult)) {
                validResult = true;
                break;
            }
        }
        assertTrue(validResult);
        if (huntingResult.equals("I guess that's why they don't call it killing. You couldn't get a shot on an animal.")) {
            assertEquals(3, player.getMorale());
            assertEquals(
                    Optional.of(1000.0).get(), player.getShelter().getFoodCache().get(porcupine));
            assertEquals(Optional.of(1000.0).get(), player.getShelter().getFoodCache().get(moose));
            assertEquals(HIGH_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.HIGH.getHydrationCost(SuccessRate.LOW), player.getHydration());
        } else if (huntingResult.equals("Watch out for those quills! You killed a nice fat porcupine that should keep you fed for a while.")) {
            assertEquals(7, player.getMorale());
            assertEquals(
                    Optional.of(1000.0 + porcupine.getGrams()).get(),
                    player.getShelter().getFoodCache().get(porcupine));
            assertEquals(HIGH_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.HIGH.getHydrationCost(SuccessRate.MEDIUM), player.getHydration());
        } else {
            assertEquals(9, player.getMorale());
            assertEquals(
                    Optional.of(1000.0 + moose.getGrams()).get(),
                    player.getShelter().getFoodCache().get(moose));
            assertEquals(HIGH_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.HIGH.getHydrationCost(SuccessRate.HIGH), player.getHydration());
        }
    }

    @Test
    public void testGoHuntingWithItems() {
        getItemFromShelter.act(new Choice("knife", player, (ItemFactory.getNewInstance("Knife"))));
        getItemFromShelter.act(new Choice("bow", player, (ItemFactory.getNewInstance("Bow"))));
        getItemFromShelter.act(new Choice("arrows", player, (ItemFactory.getNewInstance("Arrow"))));
        int previousHydration = player.getHydration();
        String huntingResult = goHunting.act(new Choice("hunting", player));
        String[] possibleResults = new String[]{"I guess that's why they don't call it killing. You couldn't get a shot on an animal.",
                "Watch out for those quills! You killed a nice fat porcupine that should keep you fed for a while.",
                "Moose down! It took five trips, but you were able to process the meat and transport it back to your shelter before a predator got to it first."};
        boolean validResult = false;
        for (String possibleResult : possibleResults) {
            if (huntingResult.equals(possibleResult)) {
                validResult = true;
                break;
            }
        }
        assertTrue(validResult);
        if (huntingResult.equals("I guess that's why they don't call it killing. You couldn't get a shot on an animal.")) {
            assertEquals(3, player.getMorale());
            assertEquals(
                    Optional.of(1000.0).get(), player.getShelter().getFoodCache().get(porcupine));
            assertEquals(Optional.of(1000.0).get(), player.getShelter().getFoodCache().get(moose));
            assertEquals(HIGH_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.HIGH.getHydrationCost(SuccessRate.LOW), player.getHydration());
        } else if (huntingResult.equals("Watch out for those quills! You killed a nice fat porcupine that should keep you fed for a while.")) {
            assertEquals(7, player.getMorale());
            assertEquals(
                    Optional.of(1000.0 + porcupine.getGrams() + porcupine.getGrams() * 0.1).get(),
                    player.getShelter().getFoodCache().get(porcupine),
                    0.001);
            assertEquals(HIGH_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.HIGH.getHydrationCost(SuccessRate.MEDIUM), player.getHydration());
        } else {
            assertEquals(9, player.getMorale());
            assertEquals(
                    Optional.of(1000.0 + moose.getGrams() + moose.getGrams() * 0.1).get(),
                    player.getShelter().getFoodCache().get(moose),
                    0.001);
            assertEquals(HIGH_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.HIGH.getHydrationCost(SuccessRate.HIGH), player.getHydration());
        }
    }

}