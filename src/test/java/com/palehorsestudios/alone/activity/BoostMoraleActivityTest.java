package com.palehorsestudios.alone.activity;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.player.Player;
import com.palehorsestudios.alone.player.SuccessRate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoostMoraleActivityTest {

    static final double LOW_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT = 179.8;

    Logger logger = Logger.getLogger(BoostMoraleActivityTest.class.getName());
    Activity getItemFromShelter;
    Activity boostMorale;
    Player player;

    @Before
    public void setUp() {
        getItemFromShelter = GetItemActivity.getInstance();
        boostMorale = BoostMoraleActivity.getInstance();
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
    public void testBoostMoraleWithoutItems() {
        int previousMorale = player.getMorale();
        int previousHydration = player.getHydration();
        String boostMoraleResult = boostMorale.act(new Choice("boost morale", player));
        int boostedMorale = player.getMorale() - previousMorale;
        assertEquals(-1, boostedMorale);
        assertEquals(LOW_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
        assertEquals(previousHydration - ActivityLevel.LOW.getHydrationCost(SuccessRate.LOW), player.getHydration());
        assertEquals("It is cold and sad here. You wish you had something to lift your spirits. " +
                "Do you want to take some rest?", boostMoraleResult);
    }

    @Test
    public void testBoostMoraleWitItems() {
        getItemFromShelter.act(new Choice("get harmonica", player, (ItemFactory.getNewInstance("Harmonica"))));
        int previousMorale = player.getMorale();
        int previousHydration = player.getHydration();
        String boostMoraleResult = boostMorale.act(new Choice("play harmonica", player, (ItemFactory.getNewInstance("Harmonica"))));
        int boostedMorale = player.getMorale() - previousMorale;
        int[] moralePossibilities = new int[]{1, 2, 3};
        boolean validMoralePossibility = false;
        for (int moralePossibility : moralePossibilities) {
            if (boostedMorale == moralePossibility) {
                validMoralePossibility = true;
                break;
            }
        }
        assertTrue(validMoralePossibility);
        assertEquals(LOW_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
        Assert.assertEquals(previousHydration - ActivityLevel.LOW.getHydrationCost(SuccessRate.LOW), player.getHydration());
        if (boostedMorale == 2) {
            assertEquals("You played your harmonica for an hour. Your morale is high now!",
                    boostMoraleResult);
        } else if (boostedMorale == 3) {
            assertEquals("You found your family photo, and it reminds you all the good memories with your family! Your" +
                    " morale is high now!", boostMoraleResult);
        } else {
            assertEquals("You decide to capture your current experience in your journal. " +
                    "You are feeling much better.", boostMoraleResult);
        }
    }
}
