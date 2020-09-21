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
import static org.junit.Assert.assertTrue;

public class GatherFirewoodActivityTest {
    static final double MED_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT = 179.6;
    static final double MED_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT = 179.2;
    static final double MED_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT = 178.4;

    Logger logger = Logger.getLogger(GatherFirewoodActivityTest.class.getName());
    Activity getItemFromShelter;
    Activity gatherFirewood;
    Player player;

    @Before
    public void setUp() {
        gatherFirewood = GatherFirewoodActivity.getInstance();
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
    public void testGatherFirewoodWithoutItems() {
        int previousHydration = player.getHydration();
        double previousFirewood = player.getShelter().getFirewood();
        String gatherFirewoodResult = gatherFirewood.act(new Choice("gather", player));
        double firewoodChange = player.getShelter().getFirewood() - previousFirewood;
        boolean validFirewoodChange = false;
        double[] validFirewoodChangeValues = new double[]{1.0, 3.0, 5.0};
        for (double validFirewoodChangeValue : validFirewoodChangeValues) {
            if (firewoodChange == validFirewoodChangeValue) {
                validFirewoodChange = true;
                break;
            }
        }
        assertTrue(validFirewoodChange);
        if (firewoodChange == 1.0) {
            assertEquals(MED_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.LOW), player.getHydration());
            assertEquals(16.0, player.getMorale(), 0.01);
        } else if (firewoodChange == 3.0) {
            assertEquals(MED_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.MEDIUM), player.getHydration());
            assertEquals(17.0, player.getMorale(), 0.01);
        } else if (firewoodChange == 5.0) {
            assertEquals(MED_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.HIGH), player.getHydration());
            assertEquals(18.0, player.getMorale(), 0.01);
        }
    }

    @Test
    public void testGatherFirewoodWithItems() {
        getItemFromShelter.act(new Choice("axe", player, (ItemFactory.getNewInstance("Axe"))));
        int previousHydration = player.getHydration();
        double previousFirewood = player.getShelter().getFirewood();
        String gatherFirewoodResult = gatherFirewood.act(new Choice("gather", player));
        double firewoodChange = player.getShelter().getFirewood() - previousFirewood;
        boolean validFirewoodChange = false;
        double[] validFirewoodChangeValues = new double[]{1.1, 3.3, 5.5};
        for (double validFirewoodChangeValue : validFirewoodChangeValues) {
            if (firewoodChange == validFirewoodChangeValue) {
                validFirewoodChange = true;
                break;
            }
        }
        assertTrue(validFirewoodChange);
        if (firewoodChange == 1.1) {
            assertEquals(MED_ACTIVITY_LOW_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.LOW), player.getHydration());
            assertEquals(16.0, player.getMorale(), 0.01);
        } else if (firewoodChange == 3.3) {
            assertEquals(MED_ACTIVITY_MED_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.MEDIUM), player.getHydration());
            assertEquals(17.0, player.getMorale(), 0.01);
        } else if (firewoodChange == 5.5) {
            assertEquals(MED_ACTIVITY_HIGH_SUCCESS_PLAYER_WEIGHT, player.getWeight(), 0.005);
            assertEquals(previousHydration - ActivityLevel.MEDIUM.getHydrationCost(SuccessRate.HIGH), player.getHydration());
            assertEquals(18.0, player.getMorale(), 0.01);
        }
    }
}