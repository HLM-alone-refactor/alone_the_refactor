package com.palehorsestudios.alone.nightencounter;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.activity.Activity;
import com.palehorsestudios.alone.activity.GetItemActivity;
import com.palehorsestudios.alone.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BearEncounterNightTest {

    Logger logger = Logger.getLogger(BearEncounterNightTest.class.getName());
    NightEncounter bearEncounterNight;
    Activity getItemFromShelter;
    Player player;

    @Before
    public void setUp() {
        bearEncounterNight = BearEncounterNight.getInstance();
        getItemFromShelter = GetItemActivity.getInstance();
    }

    @Test
    public void testBearEncounterNightKnifeAndSurvivalManualPlayerLives() {
        player = new Player(ItemFactory.getNewInstances("Knife", "Survival Manual"));
        player.getShelter().addEquipment(ItemFactory.getNewInstance("Knife"), 1);
        player.getShelter().addEquipment(ItemFactory.getNewInstance("Survival Manual"), 1);
        String encounterResult = bearEncounterNight.encounter(player);
        String expectedString =
                "You wake in the middle of the night... something is nearby. \n"
                        + "You hear a coarse, weighty breathiness, the kind only a bear might make. Instinctively, "
                        + "you reach for your knife. With it in hand, you slowly lift your head to glimpse at "
                        + "the disturbance. \n"
                        + "A massive grizzly sniffs about your camp, and he is making his way towards you and "
                        + "your food cache. You jump up and make yourself as large as you can, screaming and "
                        + "acting insane. The bear roars but hesitates. It's an intense showdown of the bear "
                        + "charging and acting as if he's going to attack, only to stop and seemingly rethink if "
                        + "he wants to tangle with you. You swipe at the air with your knife, and continue your "
                        + "antics, somehow able to hold your nerve in the face of certain death. "
                        + "Eventually, the bear backs down, and disappears into the darkness. \n"
                        + "Reading that survival manual has paid off, although you have a sinking feeling that "
                        + "the bear may be back.";
        boolean validResult = false;
        if (encounterResult.equals(expectedString)) {
            validResult = true;
        }
        assertTrue(validResult);
        assertEquals(4, player.getMorale(), .001);
    }

    @Test
    public void testBearEncounterNightNoItemsPlayerDies() {
        player = new Player(List.of());
        this.player.updateMorale(-1);
        String encounterResult = bearEncounterNight.encounter(player);
        Map foodCacheExpected = player.getShelter().getFoodCache();
        String expectedString =
                "You wake in the middle of the night... something is nearby. \n"
                        + "You hear a coarse, weighty breathiness, the kind only a bear might make. Instinctively, "
                        + "you slowly lift your head to glimpse at the disturbance. \n "
                        + "A massive grizzly sniffs about your camp, and he is making his way towards you and "
                        + "your food cache. \n"
                        + "You've heard stories about people surviving bear attacks by playing dead. "
                        + "Without much in the way of being able to defend yourself, you decide the best thing "
                        + "for you to do is to feign your death in hopes that the grizzly will ignore you in "
                        + "favor of the food you have stored in your cache. \n"
                        + "Amazingly, the bear remains throughout the night, eating its fill, though it never "
                        + "bothers you. Exhausted, shaken, and hungry, you watch the sun rise feeling lucky that "
                        + "you survived. Unfortunately, as you were already somewhat malnourished, you are"
                        + "gripped with hypothermia from your starvation and lack of calories from bodily "
                        + "sources. You have died.";
        boolean validResultString = false;
        boolean validResultMap = false;
        if (encounterResult.equals(expectedString)) {
            validResultString = true;
        }
        if (foodCacheExpected.equals(player.getShelter().getFoodCache())) {
            validResultMap = true;
        }
        assertTrue(validResultMap);
        assertTrue(validResultString);
        assertTrue(player.isDead());
        assertEquals(0, player.getMorale(), .001);
    }

    @Test
    public void testBearEncounterNightNoItemsPlayerLives() {
        player = new Player(List.of());
        String encounterResult = bearEncounterNight.encounter(player);
        Map foodCacheExpected = player.getShelter().getFoodCache();
        String expectedString =
                "You wake in the middle of the night... something is nearby. \n"
                        + "You hear a coarse, weighty breathiness, the kind only a bear might make. Instinctively, "
                        + "you slowly lift your head to glimpse at the disturbance. \n "
                        + "A massive grizzly sniffs about your camp, and he is making his way towards you and "
                        + "your food cache. \n"
                        + "You've heard stories about people surviving bear attacks by playing dead. "
                        + "Without much in the way of being able to defend yourself, you decide the best thing "
                        + "for you to do is to feign your death in hopes that the grizzly will ignore you in "
                        + "favor of the food you have stored in your cache. \n"
                        + "Amazingly, the bear remains throughout the night, eating its fill, though it never "
                        + "bothers you. Exhausted, shaken, and hungry, you watch the sun rise feeling lucky that "
                        + "you survived. Unfortunately, it seems you'll have to start over on your rations!";
        boolean validResultString = false;
        boolean validResultMap = false;
        if (encounterResult.equals(expectedString)) {
            validResultString = true;
        }
        if (foodCacheExpected.equals(player.getShelter().getFoodCache())) {
            validResultMap = true;
        }
        assertTrue(validResultMap);
        assertTrue(validResultString);
        assertEquals(1, player.getMorale(), .001);
    }

    @Test
    public void testBearEncounterNightWithPistolAndCartridgesItems() {
        player = new Player(ItemFactory.getNewInstances("Pistol Cartridge", "Pistol", "Pistol Round", "Pistol Round", "Pistol Round"));
        getItemFromShelter.act(new Choice("pistol", player, (ItemFactory.getNewInstance("Pistol"))));
        getItemFromShelter.act(new Choice("ammo", player, (ItemFactory.getNewInstance("Pistol Cartridge"))));
        String encounterResult = bearEncounterNight.encounter(player);
        String expectedString =
                "You wake in the middle of the night... something is nearby. \n"
                        + "You hear a coarse, weighty breathiness, the kind only a bear might make. Instinctively, "
                        + "you reach for your pistol. With it in hand, you open the cylinder to make sure it's loaded "
                        + "and slowly lift your head to glimpse at the disturbance. \n"
                        + "A massive grizzly sniffs about your camp, and he is making his way towards you and your "
                        + "food cache. You slowly level your pistol center mass at the bear, and fire three shots "
                        + "in quick succession. The bear attempts to bite at whatever is stinging him, but your "
                        + "aim is true and the bear slumps to the ground. You set about harvesting the bear before "
                        + "the meat goes to waste.";
        boolean validResult = false;
        if (encounterResult.equals(expectedString)) {
            validResult = true;
        }
        assertTrue(validResult);
        assertEquals(14, player.getHydration(), .001);
        assertEquals(14, player.getMorale(), .001);
        assertEquals(178.2, player.getWeight(), .001);
        assertTrue(!player.getShelter().getEquipment().containsKey(ItemFactory.getNewInstance("Pistol Round")));
    }

    @Test
    public void testBearEncounterNightWithPistolAndCartridgesItemsOnPlayer() {
        player = new Player(List.of());
        player.getItems().addAll(ItemFactory.getNewInstances("Pistol Cartridge", "Pistol", "Pistol Round", "Pistol Round", "Pistol Round"));
        getItemFromShelter.act(new Choice("pistol", player, (ItemFactory.getNewInstance("Pistol"))));
        getItemFromShelter.act(new Choice("ammo", player, (ItemFactory.getNewInstance("Pistol Cartridge"))));
        String encounterResult = bearEncounterNight.encounter(player);
        String expectedString =
                "You wake in the middle of the night... something is nearby. \n"
                        + "You hear a coarse, weighty breathiness, the kind only a bear might make. Instinctively, "
                        + "you reach for your pistol. With it in hand, you open the cylinder to make sure it's loaded "
                        + "and slowly lift your head to glimpse at the disturbance. \n"
                        + "A massive grizzly sniffs about your camp, and he is making his way towards you and your "
                        + "food cache. You slowly level your pistol center mass at the bear, and fire three shots "
                        + "in quick succession. The bear attempts to bite at whatever is stinging him, but your "
                        + "aim is true and the bear slumps to the ground. You set about harvesting the bear before "
                        + "the meat goes to waste.";
        boolean validResult = false;
        if (encounterResult.equals(expectedString)) {
            validResult = true;
        }
        assertTrue(validResult);
        assertEquals(14, player.getHydration(), .001);
        assertEquals(14, player.getMorale(), .001);
        assertEquals(178.2, player.getWeight(), .001);
        assertTrue(!player.getItems().contains(ItemFactory.getNewInstance("Pistol Round")));
    }

    @Test
    public void testBearEncounterNightWithPistolAndCartridges_itemsOnPlayerAndShelter() {
        player = new Player(ItemFactory.getNewInstances("Pistol", "Pistol Round"));
        player.getItems().addAll(ItemFactory.getNewInstances("Pistol Cartridge", "Pistol Round"));
        getItemFromShelter.act(new Choice("pistol", player, (ItemFactory.getNewInstance("Pistol"))));
        getItemFromShelter.act(new Choice("ammo", player, (ItemFactory.getNewInstance("Pistol Cartridge"))));
        String encounterResult = bearEncounterNight.encounter(player);
        String expectedString =
                "You wake in the middle of the night... something is nearby. \n"
                        + "You hear a coarse, weighty breathiness, the kind only a bear might make. Instinctively, "
                        + "you reach for your pistol. With it in hand, you open the cylinder to make sure it's loaded "
                        + "and slowly lift your head to glimpse at the disturbance. \n"
                        + "A massive grizzly sniffs about your camp, and he is making his way towards you and your "
                        + "food cache. You slowly level your pistol center mass at the bear, and fire three shots "
                        + "in quick succession. The bear attempts to bite at whatever is stinging him, but your "
                        + "aim is true and the bear slumps to the ground. You set about harvesting the bear before "
                        + "the meat goes to waste.";
        boolean validResult = false;
        if (encounterResult.equals(expectedString)) {
            validResult = true;
        }
        assertTrue(validResult);
        assertEquals(14, player.getHydration(), .001);
        assertEquals(14, player.getMorale(), .001);
        assertEquals(178.2, player.getWeight(), .001);
        assertTrue(!player.getItems().contains(ItemFactory.getNewInstance("Pistol Round")));
        assertTrue(!player.getShelter().getEquipment().containsKey(ItemFactory.getNewInstance("Pistol Round")));
    }



    @Test
    public void testBearEncounterNightWithKnifeAndSurvivalManualItemsPlayerSurvives() {
        player.getShelter().addEquipment(ItemFactory.getNewInstance("Knife"), 1);
        player.getShelter().addEquipment(ItemFactory.getNewInstance("Survival Manual"), 1);
        getItemFromShelter.act(new Choice("knife", player, (ItemFactory.getNewInstance("Knife"))));
        String encounterResult = bearEncounterNight.encounter(player);
        String expectedString =
                "You wake in the middle of the night... something is nearby. \n"
                        + "You hear a coarse, weighty breathiness, the kind only a bear might make. Instinctively, "
                        + "you reach for your knife. With it in hand, you slowly lift your head to glimpse at "
                        + "the disturbance. \n"
                        + "A massive grizzly sniffs about your camp, and he is making his way towards you and "
                        + "your food cache. You jump up and make yourself as large as you can, screaming and "
                        + "acting insane. The bear roars but hesitates. It's an intense showdown of the bear "
                        + "charging and acting as if he's going to attack, only to stop and seemingly rethink if "
                        + "he wants to tangle with you. You swipe at the air with your knife, and continue your "
                        + "antics, somehow able to hold your nerve in the face of certain death. "
                        + "Eventually, the bear backs down, and disappears into the darkness. \n"
                        + "Reading that survival manual has paid off, although you have a sinking feeling that "
                        + "the bear may be back.";
        boolean validResult = false;
        if (encounterResult.equals(expectedString)) {
            validResult = true;
        }
        assertTrue(validResult);
        assertEquals(4, player.getMorale(), .001);
    }
}