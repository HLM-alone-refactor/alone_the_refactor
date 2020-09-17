package com.palehorsestudios.alone.util;

import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class SavingTest {

    Player player;
    Saving saving;
    File file;
    String log;

    @Before
    public void setUp() throws Exception {
        saving = new Saving();
        file = new File("src/test/resources/saving/player1.ser");
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

        player.getItems().add(ItemFactory.getNewInstance("Knife"));

        log = "the log\n\n\twhhoop\nwhoop";
    }

    @Test
    public void delme() {
        saving.saveState("player1.ser", player, log);
    }

    @Test
    public void testsavePlayer() {
        File file = new File("src/test/resources/saving/player2.ser");

        saving.saveState(file, player, log);
        assertTrue(file.exists());
    }

    @Test
    public void readPlayer() {
        Player temp = saving.readPlayer(file);

        assertEquals(player, temp);
    }

    @Test
    public void readLog() {
        String result = saving.readLog(file);

        assertEquals(log, result);
    }
}