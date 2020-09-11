package com.palehorsestudios.alone.Craft;

import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.player.Player;

public class ItemCraft implements Crafting<Item> {
    @Override
    public Item craft(Player player, String goal) {
        Item newItem;
        try {
            newItem = ItemFactory.getNewInstance(goal);
//            if (newItem.)
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot craft an unknown item");
        }
        return null;
    }
}
