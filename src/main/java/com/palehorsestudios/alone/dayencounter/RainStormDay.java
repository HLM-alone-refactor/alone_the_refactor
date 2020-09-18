package com.palehorsestudios.alone.dayencounter;

import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.dayencounter.DayEncounter;
import com.palehorsestudios.alone.player.Player;

public class RainStormDay extends DayEncounter {
    private static DayEncounter encounter;

    private RainStormDay() {
    }

    public static DayEncounter getInstance() {
        if (encounter == null) {
            encounter = new RainStormDay();
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
            player.updateWeight(-100);
            player.updateMorale(+2);
            // description if the player has waterproof bag
            // to protect his gear while foraging during the storm
            return "Rain Storm with Backpack" + "The rainbow had been swallowed by a +" +
                    "rushing thundercloud. The sun darkened and for a brief moment +" +
                    "raindrops fell on the woods. \n" +
                    "You see the first lightning descend in the distance, and the thunder +" +
                    "rumbled a short time later and was reinforced by the multiple echos +" +
                    "throughout the woods. A second flash of light flashed through the clouds +" +
                    "and hit somewhere on the other side of the woods. The thunder rumbled again, +" +
                    "echoing again. \n" +
                    "The storm came closer. A flash of light illuminated the sky and the rain +" +
                    "increased significantly. Previously only a few drops had fallen down here and +" +
                    "there. Now the rain pelted down so vehemently that that you could no +" +
                    "longer see in front of you.  The rain is getting stronger, and with +" +
                    "those flashes, it's not good to sit under a tree! \n";
        } else if (player.getShelter().getEquipment().containsKey(tarp)
                && player.getShelter().getEquipment().containsKey(manual)
                && player.getShelter().getEquipment().containsKey(chord)) {

            // description if the player keeps equipment dry with Tarp
            // and survival knowledge at shelter
            player.updateMorale(-1);
            return "Rain storm with Tarp / Chord and no backpack" +
                    "The rainbow had been swallowed by a +" +
                    "rushing thundercloud. The sun darkened and for a brief moment +" +
                    "raindrops fell on the woods. \n" +
                    "You see the first lightning descend in the distance, and the thunder +" +
                    "rumbled a short time later and was reinforced by the multiple echo +" +
                    "throughout the woods. A second flash of light flashed through the clouds +" +
                    "and hit somewhere on the other side of the woods. The thunder rumbled again, +" +
                    "echoing again. \n" +
                    "The storm came closer. A flash of light illuminated the sky and the rain +" +
                    "increased significantly. Previously only a few drops had fallen down here and +" +
                    "there. Now the rain pelted down so vehemently that that you could no +" +
                    "longer see in front of you.  The rain is getting stronger, and with +" +
                    "those flashes, it's not good to sit under a tree! \n";
        } else {
            player.updateMorale(-4);
            player.getItems().clear();
            // description for if the player does not have anything to shelter his items outside his shelter
            if (player.isDead()) {
                return "Rain storm without items to protect equipment ";
            }
            return "rain" + "Rain Storm with Backpack" +
                    "The rainbow had been swallowed by a +" +
                    "rushing thundercloud. The sun darkened and for a brief moment +" +
                    "raindrops fell on the woods. \n" +
                    "You see the first lightning descend in the distance, and the thunder +" +
                    "rumbled a short time later and was reinforced by the multiple echo +" +
                    "throughout the woods. A second flash of light flashed through the clouds +" +
                    "and hit somewhere on the other side of the woods. The thunder rumbled again, +" +
                    "echoing again. \n" +
                    "The storm came closer. A flash of light illuminated the sky and the rain +" +
                    "increased significantly. Previously only a few drops had fallen down here and +" +
                    "there. Now the rain pelted down so vehemently that that you could no +" +
                    "longer see in front of you.  The rain is getting stronger, and with +" +
                    "those flashes, it's not good to sit under a tree! \n";
        }
    }
}
