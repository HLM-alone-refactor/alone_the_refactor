package com.palehorsestudios.alone.nightencounter;

import com.palehorsestudios.alone.Foods.Food;
import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.player.Player;

import java.util.*;
import java.util.stream.Collectors;

public class BearEncounterNight extends NightEncounter {

    private static NightEncounter encounter;

    private BearEncounterNight() {
    }

    public static NightEncounter getInstance() {
        if (encounter == null) {
            encounter = new BearEncounterNight();
        }
        return encounter;
    }

    @Override
    public String encounter(Player player) {

        Item pistol = ItemFactory.getNewInstance("Pistol");
        Item cartridges = ItemFactory.getNewInstance("Pistol Cartridge");
        Item rounds = ItemFactory.getNewInstance("Pistol Round");
        Item manual = ItemFactory.getNewInstance("Survival Manual");
        Item knife = ItemFactory.getNewInstance("Knife");

        int roundsInShelter = Optional.ofNullable(player.getShelter().getEquipment().get(rounds)).orElse(0);
        long roundsPlayer = player.getItems().stream().filter(e -> e.equals(rounds)).count();

        if (player.getItems().containsAll(List.of(pistol, cartridges))
                || (player.getShelter().getEquipment().containsKey(pistol)
                && player.getShelter().getEquipment().containsKey(cartridges))
                && roundsInShelter + roundsPlayer >= 3) {
//        || player.getItems().contains(BOW) && player.getItems().contains(ARROWS))
//      TODO: add else if for bow and arrow
            player.updateHydration(-1);
            player.updateWeight(-500);
            player.updateMorale(-1);
            player.getShelter().addFoodToCache(FoodFactory.getNewInstance("Bear"), FoodFactory.getNewInstance("Bear").getGrams());

            // decrement 3 rounds from player, then from shelter if they were in both
            int temp = 0;
            while (temp < 3) {
                if (!player.getItems().remove(rounds)) {
                    player.getShelter().removeEquipment(rounds, 1);
                }
                temp++;
            }

            // description for if the player defends the camp with pistol
            return "You wake in the middle of the night... something is nearby. \n"
                    + "You hear a coarse, weighty breathiness, the kind only a bear might make. Instinctively, "
                    + "you reach for your pistol. With it in hand, you open the cylinder to make sure it's loaded "
                    + "and slowly lift your head to glimpse at the disturbance. \n"
                    + "A massive grizzly sniffs about your camp, and he is making his way towards you and your "
                    + "food cache. You slowly level your pistol center mass at the bear, and fire three shots "
                    + "in quick succession. The bear attempts to bite at whatever is stinging him, but your "
                    + "aim is true and the bear slumps to the ground. You set about harvesting the bear before "
                    + "the meat goes to waste.";
        } else if (player.getShelter().getEquipment().containsKey(manual)
                && player.getItems().contains(knife)
                || player.getShelter().getEquipment().containsKey(knife)
                && player.getShelter().getEquipment().containsKey(manual)) {
//    || player.getItems().contains(HATCHET)
//    || player.getItems().contains(AXE)); {
//      TODO: add else if for hatchet and axe
            // description for if the player defends camp with knife
            player.updateMorale(-1);
            return "You wake in the middle of the night... something is nearby. \n"
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
        } else {
            player.updateMorale(-4);
            player.getShelter().getFoodCache().clear();
            // TODO: remove all food from food cache
            // description for if the player does not have anything to defend with
            if (player.isDead()) {
                return "You wake in the middle of the night... something is nearby. \n"
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
            }
            return "You wake in the middle of the night... something is nearby. \n"
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
        }
    }
}