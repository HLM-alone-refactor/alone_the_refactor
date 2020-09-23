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

public class TrapActivityTest {

    static final double MED_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT = 179.7;
    static final double MED_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT = 179.4;
    static final double MED_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT = 178.7;

    Logger logger = Logger.getLogger(TrapActivityTest.class.getName());
    Activity getItemFromShelter;
    Activity goTrapping;
    Player player;
    Food rabbit;
    Food squirrel;

    @Before
    public void setUp() {
        goTrapping = TrapActivity.getInstance();
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
        
        rabbit = FoodFactory.getNewInstance("Rabbit");
        squirrel = FoodFactory.getNewInstance("Squirrel");
    }

    @Test
    public void testGoTrappingNoItems() {
        int previousHydration = player.getHydration();
        String trappingResult = goTrapping.act(new Choice("trap", player));
        String[] possibleResults = new String[]{"Those varmints are smarter than they look. Your traps were empty.",
                "Your patience has paid off. There were two squirrels in your traps!",
                "You'll have plenty of lucky rabbit feet now. Your snared three rabbits!"};
        boolean validResult = false;
        for (String possibleResult : possibleResults) {
            if (trappingResult.equals(possibleResult)) {
                validResult = true;
                break;
            }
        }
        assertTrue(validResult);
        if (trappingResult.equals("Those varmints are smarter than they look. Your traps were empty.")) {
            assertEquals(3, player.getMorale());
            assertEquals(
                    Optional.of(1000.0).get(), player.getShelter().getFoodCache().get(squirrel));
            assertEquals(Optional.of(1000.0).get(), player.getShelter().getFoodCache().get(rabbit));
            assertEquals(MED_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.LOW), player.getHydration());
        } else if (trappingResult.equals("Your patience has paid off. There were two squirrels in your traps!")) {
            assertEquals(6, player.getMorale());
            assertEquals(
                    Optional.of(1000.0 + (squirrel.getGrams() * 2)).get(),
                    player.getShelter().getFoodCache().get(squirrel));
            assertEquals(MED_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.MEDIUM), player.getHydration());
        } else {
            assertEquals(7, player.getMorale());
            assertEquals(
                    Optional.of(1000.0 + (rabbit.getGrams() * 3)).get(),
                    player.getShelter().getFoodCache().get(rabbit));
            assertEquals(MED_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.HIGH), player.getHydration());
        }
    }

    @Test
    public void testGoTrappingWithItems() {
        getItemFromShelter.act(new Choice("wire", player, (ItemFactory.getNewInstance("Wire"))));
        int previousHydration = player.getHydration();
        String trappingResult = goTrapping.act(new Choice("trap", player));
        String[] possibleResults = new String[]{"Those varmints are smarter than they look. Your traps were empty.",
                "Your patience has paid off. There were two squirrels in your traps!",
                "You'll have plenty of lucky rabbit feet now. Your snared three rabbits!"};
        boolean validResult = false;
        for (String possibleResult : possibleResults) {
            if (trappingResult.contains(possibleResult)) {
                validResult = true;
                break;
            }
        }
        assertTrue(validResult);
        if (trappingResult.contains("Those varmints are smarter than they look. Your traps were empty.")) {
            assertEquals(3, player.getMorale());
            assertEquals(
                    Optional.of(1000.0).get(), player.getShelter().getFoodCache().get(squirrel));
            assertEquals(Optional.of(1000.0).get(), player.getShelter().getFoodCache().get(rabbit));
            assertEquals(MED_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.LOW), player.getHydration());
        } else if (trappingResult.contains("Your patience has paid off. There were two squirrels in your traps!")) {
            assertEquals(6, player.getMorale());
            assertEquals(
                    Optional.of(1000.0 + (squirrel.getGrams() * 2 + squirrel.getGrams() * 2 * 0.1))
                            .get(),
                    player.getShelter().getFoodCache().get(squirrel),
                    0.001);
            assertEquals(MED_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.MEDIUM), player.getHydration());
        } else {
            assertEquals(7, player.getMorale());
            assertEquals(
                    Optional.of(1000.0 + (rabbit.getGrams() * 3 + rabbit.getGrams() * 3 * 0.1))
                            .get(),
                    player.getShelter().getFoodCache().get(rabbit),
                    0.001);
            assertEquals(MED_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.HIGH), player.getHydration());
        }
    }
}