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

public class TrapActivity extends Activity {
    private static TrapActivity activityReference;

    private TrapActivity() {
    }

    public static Activity getInstance() {
        if (activityReference == null) {
            activityReference = new TrapActivity();
        }
        return activityReference;
    }

    @Override
    public String act(Choice choice) {
        String result;
        SuccessRate successRate = generateSuccessRate();
        double caloriesBurned = ActivityLevel.MEDIUM.getCaloriesBurned(successRate);
        choice.getPlayer().updateWeight(-caloriesBurned);
        int hydrationCost = ActivityLevel.MEDIUM.getHydrationCost(successRate);
        choice.getPlayer().updateHydration(-hydrationCost);
        double boostFactor =
                Activity.getActivityBoostFactor(ItemFactory.getNewInstances("Survival Manual", "Wire", "Knife"),
                        choice.getPlayer());
        // gear, maybe we should eliminate low success rate possibility.
        if (successRate == SuccessRate.LOW) {
            choice.getPlayer().updateMorale(-2);
            result = "Those varmints are smarter than they look. Your traps were empty.";
        } else if (successRate == SuccessRate.MEDIUM) {
            Food squirrel = FoodFactory.getNewInstance("Squirrel");
            choice
                    .getPlayer()
                    .getShelter()
                    .addFoodToCache(
                            squirrel,
                            squirrel.getGrams() * 2 + squirrel.getGrams() * 2 * boostFactor);
            choice.getPlayer().updateMorale(1);
            result = "Your patience has paid off. There were two squirrels in your traps!";
        } else {
            Food rabbit = FoodFactory.getNewInstance("Rabbit");
            choice
                    .getPlayer()
                    .getShelter()
                    .addFoodToCache(
                            rabbit, rabbit.getGrams() * 3 + rabbit.getGrams() * 3 * boostFactor);
            choice.getPlayer().updateMorale(2);
            result = "You'll have plenty of lucky rabbit feet now. Your snared three rabbits!";
        }

        result += luckFindInActivity(ItemFactory.getByFind("Trap"), choice, successRate, .7, 0);

        return result;
    }
}