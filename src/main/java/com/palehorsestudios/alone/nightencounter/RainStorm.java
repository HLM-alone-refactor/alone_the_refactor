package com.palehorsestudios.alone.nightencounter;

import com.palehorsestudios.alone.Foods.Food;
import com.palehorsestudios.alone.Foods.FoodFactory;
import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.player.Player;

public class RainStorm extends NightEncounter {
    private static NightEncounter encounter;

    private RainStorm() {
    }

    public static NightEncounter getInstance() {
        if (encounter == null) {
            encounter = new RainStorm();
        }
        return encounter;
    }

    @Override
    public String encounter(Player player) {

        Item bag = ItemFactory.getNewInstance("Waterproof Bag");
        Item tarp = ItemFactory.getNewInstance("Tarp");
        Item manual = ItemFactory.getNewInstance("Survival Manual");
        Item chord = ItemFactory.getNewInstance("Parachute Chord");

        if (player.getItems().contains(bag)) {
            player.updateWeight(-500);
            player.updateMorale(+2);
            // description for if the player has waterproof bag
            // to protect his gear while foraging during the storm
            return "Rain Storm with Backpack";
        } else if (player.getShelter().getEquipment().containsKey(tarp)
                && player.getShelter().getEquipment().containsKey(manual)
                && player.getShelter().getEquipment().containsKey(chord)) {

            // description if the player keeps equipment dry with Tarp
            // and survival knowledge at shelter
            player.updateMorale(-1);
            return "Rain storm with Tarp / Chord and no backpack";
        } else {
            player.updateMorale(-4);
            player.getShelter().getFoodCache().clear();
            // TODO: remove all food from food cache
            // description for if the player does not have anything to defend with
            if (player.isDead()) {
                return "Rain storm without items to protect equipment ";
            }
            return "rain";
        }
    }
}
