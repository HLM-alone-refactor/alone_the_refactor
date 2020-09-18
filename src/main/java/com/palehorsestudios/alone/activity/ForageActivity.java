package com.palehorsestudios.alone.activity;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Foods.Food;
import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.player.SuccessRate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class ForageActivity extends Activity {
    private static ForageActivity activityReference;

    private ForageActivity() {
    }

    public static Activity getInstance() {
        if (activityReference == null) {
            activityReference = new ForageActivity();
        }
        return activityReference;
    }

    @Override
    public String act(Choice choice) {
        String result;
        SuccessRate successRate = generateSuccessRate();

        double caloriesBurned = ActivityLevel.LOW.getCaloriesBurned(successRate);
        choice.getPlayer().updateWeight(-caloriesBurned);
        int hydrationCost = ActivityLevel.LOW.getHydrationCost(successRate);
        choice.getPlayer().setHydration(choice.getPlayer().getHydration() - hydrationCost);
        double boostFactor =
                Activity.getActivityBoostFactor(ItemFactory.getNewInstances("Survival Manual", "Boots",
                        "Knife", "Pot"), choice.getPlayer());
        // gear, maybe we should eliminate low success rate possibility.
        if (successRate == SuccessRate.LOW) {
            Food berries = FoodFactory.getNewInstance("Berries");
            choice.getPlayer().getShelter()
                    .addFoodToCache(
                            berries,
                            berries.getGrams() * 2 + berries.getGrams() * 2 * boostFactor);
            choice.getPlayer().updateMorale(1);
            result = "Lucky for you, berries are ripe this time of year. You picked as many as you could carry.";
        } else if (successRate == SuccessRate.MEDIUM) {
            Food mushroom = FoodFactory.getNewInstance("Mushroom");
            choice.getPlayer().getShelter()
                    .addFoodToCache(
                            mushroom,
                            mushroom.getGrams() * 4 + mushroom.getGrams() * 4 * boostFactor);
            choice.getPlayer().updateMorale(1);
            result = "Delicious fungus! You found a log covered in edible mushrooms.";
        } else {
            Food bug = FoodFactory.getNewInstance("Bug");
            choice.getPlayer().getShelter()
                    .addFoodToCache(
                            bug, bug.getGrams() * 3 + bug.getGrams() * 3 * boostFactor);
            choice.getPlayer().updateMorale(2);
            result = "You never thought you would say this, but you are thrilled to have found a large group "
                    + "of leaf beetles under a decayed log. These critters are packed full of protein!";
        }

        // update player with items they found
        result += luckFindInActivity(ItemFactory.getByFind("Forage"), choice, successRate, 0.8, 0);

        return result;
    }
}
