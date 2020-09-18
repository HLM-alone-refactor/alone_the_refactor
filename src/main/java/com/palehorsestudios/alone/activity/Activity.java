package com.palehorsestudios.alone.activity;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.player.Player;
import com.palehorsestudios.alone.player.SuccessRate;
import com.palehorsestudios.alone.util.HelperMethods;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Activity {
    public abstract String act(Choice choice);

    /**
     * Helper method for determining if Result of player activity gets amplified.
     *
     * @param boosterItems Items that could boost activity Result if Player possesses them.
     * @return Factor by which Player activity Result gets boosted.
     */
    public static double getActivityBoostFactor(List<Item> boosterItems, Player player) {
        double boostValue = 0.0;
        for (Item item : boosterItems) {
            if (player.getItems().contains(item)) {
                boostValue += 0.1;
            }
        }
        return boostValue;
    }

    /**
     * Helper method for generating a random SuccessRate.
     *
     * @return Random SuccessRate.
     */
    public static SuccessRate generateSuccessRate() {
        int seed = ((int) Math.floor(Math.random() * 3));
        if (seed == 0) {
            return SuccessRate.LOW;
        } else if (seed == 1) {
            return SuccessRate.MEDIUM;
        } else {
            return SuccessRate.HIGH;
        }
    }

    /*
    package private method
     */
    static String luckFindInActivity(List<Item> items, Choice choice, SuccessRate successRate, double luck, int minOnReturn) {
        StringBuilder sb = new StringBuilder();
        int repeat = (successRate == SuccessRate.LOW) ? 1 : (successRate == SuccessRate.MEDIUM) ? 2 : 3;
        for (int i = 0; i < repeat; i++) {
            int amount = HelperMethods.doByPercentTarget(luck) ? ThreadLocalRandom.current().nextInt(minOnReturn,
                    3) : 0;
            if (amount > 0) {
                Item item = items.get(ThreadLocalRandom.current().nextInt(items.size()));
                choice.getPlayer().getShelter().addEquipment(item, amount);
                sb.append("\nLucky~You! You found " + amount + " " + item.getType() + "!");
            }

        }

        return sb.toString();
    }
}
