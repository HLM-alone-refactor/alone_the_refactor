package com.palehorsestudios.alone.activity;

import com.palehorsestudios.alone.Choice;
import com.palehorsestudios.alone.Items.Craft;
import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CraftActivity extends Activity {

    private static CraftActivity activityReference;

    private CraftActivity() {
    }

    public static Activity getInstance() {
        if (activityReference == null) {
            activityReference = new CraftActivity();
        }
        return activityReference;
    }

    @Override
    public String act(Choice choice) {
        try {
            List<String> playerItems = choice.getPlayer().getItems().stream().map(Item::getType).collect(Collectors.toList());

            if (choice.getItem().isCraftable()) {
                // go through each craft object and see if requirements for one of them is met
                for (Craft craft : choice.getItem().getCraft()) {
                    // if player has all the items needed to craft it
                    if (craft.getRequirements().keySet().stream()
                            .filter(playerItems::contains).count() == craft.getRequirements().keySet().size()) {
                        // make sure to remove extra items of the same kind (if applicable)
                        for (var entry : craft.getRequirements().entrySet()) {
                            for (int i = 0; i < entry.getValue(); i++) {
                                choice.getPlayer().getItems().remove(ItemFactory.getNewInstance(entry.getKey()));
                            }
                        }

                        // return the new item
                        choice.getPlayer().getItems().add(choice.getItem());
                        return "Successfully crafted " + choice.getItem().getType() + "!";
                    }
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Cannot craft an unknown item");
        }

        return "Failed at crafting " + choice.getItem().getType() +", missing required items.";
    }
}
