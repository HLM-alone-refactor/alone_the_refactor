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

public class FishActivityTest {

    static final double MED_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT = 179.7;
    static final double MED_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT = 179.4;
    static final double MED_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT = 178.7;

    Logger logger = Logger.getLogger(FishActivityTest.class.getName());
    Activity getItemFromShelter;
    Activity goFishing;
    Player player;

    @Before
    public void setUp() {
        goFishing = FishActivity.getInstance();
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
    }

    @Test
    public void testGoFishingNoItems() {
        int previousHydration = player.getHydration();
        String fishingResult = goFishing.act(new Choice("go fishing", player));
        String[] possibleResults = new String[]{"I guess that's why they don't call it catching. You didn't catch any fish.",
                "It looks like you'll be eating fresh fish tonight! You caught one lake trout.",
                "I hope there's room in your food cache. You caught three white fish!"};
        boolean validResult = false;
        for (String possibleResult : possibleResults) {
            if (fishingResult.equals(possibleResult)) {
                validResult = true;
                break;
            }
        }
        assertTrue(validResult);
        Food fish = FoodFactory.getNewInstance("Fish");
        if (fishingResult.equals("I guess that's why they don't call it catching. You didn't catch any fish.")) {
            assertEquals(3, player.getMorale());
            assertEquals(
                    Optional.of(1000.0).get(), player.getShelter().getFoodCache().get(fish), 0.001);
            assertEquals(MED_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.LOW), player.getHydration());
        } else if (fishingResult.equals("It looks like you'll be eating fresh fish tonight! You caught one lake trout.")) {
            assertEquals(7, player.getMorale());
            assertEquals(
                    Optional.of(1000.0 + fish.getGrams()).get(),
                    player.getShelter().getFoodCache().get(fish),
                    0.001);
            assertEquals(MED_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.MEDIUM), player.getHydration());
        } else {
            assertEquals(8, player.getMorale());
            assertEquals(
                    Optional.of(1000.0 + (fish.getGrams() * 3)).get(),
                    player.getShelter().getFoodCache().get(fish),
                    0.001);
            assertEquals(MED_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.HIGH), player.getHydration());
        }
    }

    @Test
    public void testGoFishingWithItems() {
        getItemFromShelter.act(new Choice("fishing line", player, (ItemFactory.getNewInstance("Fishing Line"))));
        getItemFromShelter.act(new Choice("fishing hooks", player, (ItemFactory.getNewInstance("Fishing Hooks"))));
        getItemFromShelter.act(new Choice("fishing lures", player, (ItemFactory.getNewInstance("Fishing Lures"))));
        int previousHydration = player.getHydration();
        String fishingResult = goFishing.act(new Choice("go fishing", player));
        String[] possibleResults = new String[]{"I guess that's why they don't call it catching. You didn't catch any fish.",
                "It looks like you'll be eating fresh fish tonight! You caught one lake trout.",
                "I hope there's room in your food cache. You caught three white fish!"};
        boolean validResult = false;
        for (String possibleResult : possibleResults) {
            if (fishingResult.equals(possibleResult)) {
                validResult = true;
                break;
            }
        }
        assertTrue(validResult);
        Food fish = FoodFactory.getNewInstance("Fish");
        if (fishingResult.equals("I guess that's why they don't call it catching. You didn't catch any fish.")) {
            assertEquals(3, player.getMorale());
            assertEquals(
                    Optional.of(1000.0).get(), player.getShelter().getFoodCache().get(fish), 0.001);
            assertEquals(MED_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.LOW), player.getHydration());
        } else if (fishingResult.equals("It looks like you'll be eating fresh fish tonight! You caught one lake trout.")) {
            assertEquals(7, player.getMorale());
            assertEquals(
                    Optional.of(1000.0 + fish.getGrams() + fish.getGrams() * 0.2).get(),
                    player.getShelter().getFoodCache().get(fish),
                    0.001);
            assertEquals(MED_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.MEDIUM), player.getHydration());
        } else {
            assertEquals(8, player.getMorale());
            assertEquals(
                    Optional.of(1000.0 + (fish.getGrams() * 3 + fish.getGrams() * 3 * 0.2)).get(),
                    player.getShelter().getFoodCache().get(fish),
                    0.001);
            assertEquals(MED_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.HIGH), player.getHydration());
        }
    }
}