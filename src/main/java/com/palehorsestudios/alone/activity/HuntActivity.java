package com.palehorsestudios.alone.activity;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Foods.Food;
import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.player.SuccessRate;

public class HuntActivity extends Activity {
    private static HuntActivity activityReference;

    private HuntActivity() {
    }

    public static Activity getInstance() {
        if (activityReference == null) {
            activityReference = new HuntActivity();
        }
        return activityReference;
    }

    @Override
    public String act(Choice choice) {
        String result;
        SuccessRate successRate = generateSuccessRate();
        double caloriesBurned = ActivityLevel.HIGH.getCaloriesBurned(successRate);
        choice.getPlayer().updateWeight(-caloriesBurned);
        int hydrationCost = ActivityLevel.HIGH.getHydrationCost(successRate);
        choice.getPlayer().setHydration(choice.getPlayer().getHydration() - hydrationCost);
        // get boost factor based on items the player is carrying
        double boostFactor =
                Activity.getActivityBoostFactor(ItemFactory.getNewInstances("Survival Manual", "Arrow",
                        "Bow", "Pistol", "Pistol Cartridge", "Knife"), choice.getPlayer());
        // gear, maybe we should eliminate low success rate possibility.
        if (successRate == SuccessRate.LOW) {
            choice.getPlayer().updateMorale(-2);
            result = "I guess that's why they don't call it killing. You couldn't get a shot on an animal.";
        } else if (successRate == SuccessRate.MEDIUM) {
            Food porcupine = FoodFactory.getNewInstance("Porcupine");
            choice.getPlayer().getShelter()
                    .addFoodToCache(
                            porcupine, porcupine.getGrams() + porcupine.getGrams() * boostFactor);
            choice.getPlayer().updateMorale(2);
            result = "Watch out for those quills! You killed a nice fat porcupine that should keep you fed for a while.";
        } else {
            Food moose = FoodFactory.getNewInstance("Moose");
            choice.getPlayer().getShelter()
                    .addFoodToCache(moose, moose.getGrams() + moose.getGrams() * boostFactor);
            choice.getPlayer().updateMorale(4);
            result = "Moose down! It took five trips, but you were able to process the meat and transport it back to " +
                    "your shelter before a predator got to it first.";
        }
        return result;
    }
}
