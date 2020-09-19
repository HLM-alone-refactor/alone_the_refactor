package com.palehorsestudios.alone.activity;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CraftActivityTest {

    Player player;

    @Before
    public void setUp() throws Exception {
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
    public void testCraft_playerHasItems() {
        player.getItems().addAll(ItemFactory.getNewInstances("Wood", "Rock"));

        assertEquals(ItemFactory.getNewInstances("Wood", "Rock"), player.getItems());

        assertEquals("Successfully crafted Knife!", CraftActivity.getInstance().act(new Choice("Craft", player, ItemFactory.getNewInstance("Knife"))));
        assertEquals(ItemFactory.getNewInstances("Knife"), player.getItems());
    }

    @Test
    public void testCraft_anArrow() {
        player.getItems().addAll(ItemFactory.getNewInstances("Leaf", "Rock", "Wood"));

        assertEquals("Successfully crafted Arrow!", CraftActivity.getInstance().act(new Choice("Craft", player, ItemFactory.getNewInstance("Arrow"))));
    }

    @Test
    public void testCraft_requirementMoreThanOne() {
        player.getItems().addAll(ItemFactory.getNewInstances("Wood", "Yarn", "Yarn"));
        CraftActivity.getInstance().act(new Choice("Craft", player, ItemFactory.getNewInstance("Bow")));

        assertTrue(player.getItems().contains(ItemFactory.getNewInstance("Bow")));
    }

    @Test
    public void testCraft_playerDoesntHaveItems() {
        player.getItems().addAll(ItemFactory.getNewInstances("Wood"));

        assertEquals(ItemFactory.getNewInstances("Wood"), player.getItems());
        assertEquals("Failed at crafting Knife, missing required items.", CraftActivity.getInstance().act(new Choice("Knife", player)));
        assertEquals(ItemFactory.getNewInstances("Wood"), player.getItems());
    }

}