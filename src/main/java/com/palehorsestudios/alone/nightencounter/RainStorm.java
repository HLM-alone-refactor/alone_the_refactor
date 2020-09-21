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
            player.updateWeight(-100);
            player.updateMorale(+2);
            // description if the player has waterproof bag
            // to protect his gear while foraging during the storm
            return "The sky has been swallowed by a rushing thundercloud. \n" +
                    "The sky darkened and for a brief moment raindrops fell on the woods. \n" +
                    "The storm came closer. A flash of light illuminated the sky and the rain \n" +
                    "increased significantly. Previously only a few drops had fallen down here and \n" +
                    "there. Now the rain pelted down so vehemently that you could no longer \n" +
                    "see in front of you.  The rain is getting stronger, and with those flashes, \n" +
                    "you become drenched. Fortunatley you have waterproof to protect your critical \n" +
                    "inventory items. However this rain is taking its toll on your morale. \n " +
                    "You should get to shelter quickly. Its not good to sit under a these trees \n";

        } else if (player.getShelter().getEquipment().containsKey(tarp)
                && player.getShelter().getEquipment().containsKey(manual)
                && player.getShelter().getEquipment().containsKey(chord)) {

            // description if the player keeps equipment dry with Tarp
            // and survival knowledge at shelter
            player.updateMorale(-1);
            return "The sky has been swallowed by a rushing thundercloud. \n" +
                    "The sky darkened and for a brief moment raindrops fell on the woods. \n" +
                    "The storm came closer. A flash of light illuminated the sky and the rain \n" +
                    "increased significantly. Previously only a few drops had fallen down here and \n" +
                    "there. Now the rain pelted down so vehemently that you could no longer \n" +
                    "see in front of you.  The rain is getting stronger, and with those flashes, \n" +
                    "you become drenched. Fortunatley, you have waterproof to protect your critical \n" +
                    "inventory items. However this rain is taking its toll on your morale. \n " +
                    "You should get to shelter quickly. Its not good to sit under a these trees \n";
        } else {
            player.updateMorale(-4);
            player.getItems().clear();
            // description for if the player does not have anything to shelter his items outside his shelter

            if (player.isDead()) {
                return "The sky has been swallowed by a rushing thundercloud. \n" +
                        "The sky darkened and for a brief moment raindrops fell on the woods. \n" +
                        "The storm came closer. A flash of light illuminated the sky and the rain \n" +
                        "increased significantly. Previously only a few drops had fallen down here and \n" +
                        "there. Now the rain pelted down so vehemently that you could no longer \n" +
                        "see in front of you.  The rain is getting stronger, and with those flashes, \n" +
                        "you become drenched. Unfortunatley, you do not have equipment to protect your critical \n" +
                        "inventory items. You have lost your inventory. This rain is taking its toll \n " +
                        "on your morale. You should get to shelter quickly. Its not good to sit under \n" +
                        "a these trees \n";
            }
