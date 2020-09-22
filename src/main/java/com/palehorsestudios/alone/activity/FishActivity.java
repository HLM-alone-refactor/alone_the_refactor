package com.palehorsestudios.alone.activity;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Foods.Food;
import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.player.SuccessRate;
import com.palehorsestudios.alone.util.HelperMethods;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FishActivity extends Activity {
    private static FishActivity activityReference;

    private FishActivity() {
    }

    public static Activity getInstance() {
        if (activityReference == null) {
            activityReference = new FishActivity();
        }
        return activityReference;
    }

    @Override
    public String act(Choice choice) {
        String result;
        SuccessRate successRate = Activity.generateSuccessRate();
        double caloriesBurned = ActivityLevel.MEDIUM.getCaloriesBurned(successRate);
        choice.getPlayer().updateWeight(-caloriesBurned);
        int hydrationCost = ActivityLevel.MEDIUM.getHydrationCost(successRate);
        choice.getPlayer().updateHydration(-hydrationCost);
        // get boost factor based on items the player is carrying
        double boostFactor =
                Activity.getActivityBoostFactor(ItemFactory.getNewInstances("Survival Manual",
                        "Fishing Hooks", "Fishing Line", "Fishing Lures", "Fishing Bait"), choice.getPlayer());
        // gear, maybe we should eliminate low success rate possibility.
        if (successRate == SuccessRate.LOW) {
            choice.getPlayer().updateMorale(-2);
            result = "I guess that's why they don't call it catching. You didn't catch any fish.";
        } else if (successRate == SuccessRate.MEDIUM) {
            Food fish = FoodFactory.getNewInstance("Fish");
            choice.getPlayer().getShelter()
                    .addFoodToCache(fish, fish.getGrams() + fish.getGrams() * boostFactor);
            choice.getPlayer().updateMorale(2);
            result = "It looks like you'll be eating fresh fish tonight! You caught one lake trout.";
        } else {
            Food fish = FoodFactory.getNewInstance("Fish");
            choice.getPlayer().getShelter()
                    .addFoodToCache(
                            fish, fish.getGrams() * 3 + fish.getGrams() * 3 * boostFactor);
            choice.getPlayer().updateMorale(3);
            result = "I hope there's room in your food cache. You caught three white fish!";
        }

        result += luckFindInActivity(ItemFactory.getByFind("Fish"), choice, successRate, 0.9, 1);

        return result;
    }
}
