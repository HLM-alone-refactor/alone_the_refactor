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

public class EatActivityTest {

    Logger logger = Logger.getLogger(EatActivityTest.class.getName());
    Activity eat;
    Activity getFirewood;
    Activity getItemFromShelter;
    Activity buildFire;
    Player player;

    @Before
    public void setUp() {
        eat = EatActivity.getInstance();
        getFirewood = GatherFirewoodActivity.getInstance();
        getItemFromShelter = GetItemActivity.getInstance();
        buildFire = BuildFireActivity.getInstance();
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
    public void testEatHappyWithoutFire() {
        assertEquals("Fish doesn't taste very good uncooked. You should consider lighting a fire.",
                eat.act(new Choice("eat", player, FoodFactory.getNewInstance("Fish"))));
        assertEquals(180.9, player.getWeight(), 0.01);
        assertEquals(660.0, player.getShelter().getFoodCache().get(FoodFactory.getNewInstance("Fish")), 0.001);
    }

    @Test
    public void testEatHappyWithFire() {
        while (player.getShelter().getFirewood() <= 0) {
            getFirewood.act(new Choice("gather firewood", player));
        }
        getItemFromShelter.act(new Choice("get flint and steel", player, (ItemFactory.getNewInstance("Flint and Steel"))));
        while (!player.getShelter().hasFire()) {
            buildFire.act(new Choice("build fire", player));
        }
        assertEquals("You had a nice warm meal of Fish cooked over your fire.", eat.act(new Choice("eat fish", player, (FoodFactory.getNewInstance("Fish")))));
        assertEquals(660.0, player.getShelter().getFoodCache().get(FoodFactory.getNewInstance("Fish")), 0.001);
    }

    @Test
    public void testEatFail() {
        assertEquals("You don't have any Bug.", eat.act(new Choice("eat bug", player, (FoodFactory.getNewInstance("Bug")))));
    }
}