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

public class ForageActivityTest {

    static final double LOW_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT = 179.8;
    static final double LOW_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT = 179.7;
    static final double LOW_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT = 179.4;

    Logger logger = Logger.getLogger(ForageActivityTest.class.getName());
    Activity getItemFromShelter;
    Activity goForaging;
    Player player;
    Food berries;
    Food mushroom;
    Food bug;

    @Before
    public void setUp() {
        goForaging = ForageActivity.getInstance();
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
        
        berries = FoodFactory.getNewInstance("Berries");
        mushroom = FoodFactory.getNewInstance("Mushroom");
        bug = FoodFactory.getNewInstance("Bug");
    }

    @Test
    public void testGoForagingNoItems() {
        int previousHydration = player.getHydration();
        String foragingResult = goForaging.act(new Choice("forage", player));
        String[] possibleResults = new String[]{"Lucky for you, berries are ripe this time of year. You picked as many as you could carry.",
                "Delicious fungus! You found a log covered in edible mushrooms.",
                "You never thought you would say this, but you are thrilled to have found a large group "
                        + "of leaf beetles under a decayed log. These critters are packed full of protein!"};
        boolean validResult = false;
        for (String possibleResult : possibleResults) {
            if (foragingResult.contains(possibleResult)) {
                validResult = true;
                break;
            }
        }
        assertTrue(validResult);
        if (foragingResult.contains("Lucky for you, berries are ripe this time of year. You picked as many as you could carry.")) {
            assertEquals(16, player.getMorale());
            assertEquals(
                    Optional.of(berries.getGrams() * 2).get(),
                    player.getShelter().getFoodCache().get(berries));
            assertEquals(LOW_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.5);
            assertEquals(previousHydration - ActivityLevel.LOW.getHydrationCost(SuccessRate.LOW), player.getHydration());
        } else if (foragingResult.contains("Delicious fungus! You found a log covered in edible mushrooms.")) {
            assertEquals(16, player.getMorale());
            assertEquals(
                    Optional.of(mushroom.getGrams() * 4).get(),
                    player.getShelter().getFoodCache().get(mushroom),
                    0.001);
            assertEquals(LOW_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.5);
            assertEquals(previousHydration - ActivityLevel.LOW.getHydrationCost(SuccessRate.MEDIUM), player.getHydration());
        } else {
            assertEquals(17, player.getMorale());
            assertEquals(
                    Optional.of(bug.getGrams() * 3).get(),
                    player.getShelter().getFoodCache().get(bug),
                    0.001);
            assertEquals(LOW_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.5);
            assertEquals(previousHydration - ActivityLevel.LOW.getHydrationCost(SuccessRate.HIGH), player.getHydration());
        }
    }

    @Test
    public void testGoForagingWithItems() {
        getItemFromShelter.act(new Choice("pot", player, (ItemFactory.getNewInstance("Pot"))));
        getItemFromShelter.act(new Choice("pot", player, (ItemFactory.getNewInstance("Boots"))));
        int previousHydration = player.getHydration();
        String foragingResult = goForaging.act(new Choice("forage", player));
        String[] possibleResults = new String[]{"Lucky for you, berries are ripe this time of year. You picked as many as you could carry.",
                "Delicious fungus! You found a log covered in edible mushrooms.",
                "You never thought you would say this, but you are thrilled to have found a large group "
                        + "of leaf beetles under a decayed log. These critters are packed full of protein!"};
        boolean validResult = false;
        for (String possibleResult : possibleResults) {
            if (foragingResult.contains(possibleResult)) {
                validResult = true;
                break;
            }
        }
        assertTrue(validResult);
        if (foragingResult.contains("Lucky for you, berries are ripe this time of year. You picked as many as you could carry.")) {
            assertEquals(16, player.getMorale());
            assertEquals(
                    Optional.of(berries.getGrams() * 2 + berries.getGrams() * 2 * 0.1).get(),
                    player.getShelter().getFoodCache().get(berries),
                    0.001);
            assertEquals(LOW_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.5);
            assertEquals(previousHydration - ActivityLevel.LOW.getHydrationCost(SuccessRate.LOW), player.getHydration());
        } else if (foragingResult.contains("Delicious fungus! You found a log covered in edible mushrooms.")) {
            assertEquals(16, player.getMorale());
            assertEquals(
                    Optional.of(mushroom.getGrams() * 4 + mushroom.getGrams() * 4 * 0.1).get(),
                    player.getShelter().getFoodCache().get(mushroom),
                    0.001);
            assertEquals(LOW_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.5);
            assertEquals(previousHydration - ActivityLevel.LOW.getHydrationCost(SuccessRate.MEDIUM), player.getHydration());
        } else {
            assertEquals(17, player.getMorale());
            assertEquals(
                    Optional.of(bug.getGrams() * 3 + bug.getGrams() * 3 * 0.1).get(),
                    player.getShelter().getFoodCache().get(bug),
                    0.001);
            assertEquals(LOW_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.5);
            assertEquals(previousHydration - ActivityLevel.LOW.getHydrationCost(SuccessRate.HIGH), player.getHydration());
        }
    }
}