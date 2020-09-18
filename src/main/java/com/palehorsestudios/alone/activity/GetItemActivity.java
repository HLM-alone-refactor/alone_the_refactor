package com.palehorsestudios.alone.activity;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Items.ItemFactory;

import java.util.Optional;

public class GetItemActivity extends Activity {

    private static GetItemActivity activityReference;

    private GetItemActivity() {
    }

    public static Activity getInstance() {
        if (activityReference == null) {
            activityReference = new GetItemActivity();
        }
        return activityReference;
    }

    @Override
    public String act(Choice choice) {
        double maxWeightCarry = 50;
//        if (choice.getPlayer().getItems().contains(ItemFactory.getNewInstance("Backpack"))) {
//            maxWeightCarry = 100;
//        }
        String result;

      /* determine if player has less than the maximum carry limit
      and item is in shelter. */
        Optional<Integer> shelterItemCount = Optional.ofNullable
                (choice.getPlayer().getShelter().getEquipment().get(choice.getItem()));
        if (shelterItemCount.isPresent() && shelterItemCount.get() > 0) {
            if (choice.getItem().getWeight() + choice.getPlayer().getItemsWeight() < maxWeightCarry) {
                int retrievalResult = choice.getPlayer().getShelter().removeEquipment(choice.getItem(), 1);
                result = "You retrieved " + retrievalResult + " " + choice.getItem().getType() + " from your shelter.";
                choice.getPlayer().getItems().add(choice.getItem());
            } else {
                result = "You can only carry " + maxWeightCarry + "lbs of items.";
            }
        } else {
            result = "You do not have a(n) " + choice.getItem().getType() + " in your shelter.";
        }
        return result;
    }
}
