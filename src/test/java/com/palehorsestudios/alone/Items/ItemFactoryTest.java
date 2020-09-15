package com.palehorsestudios.alone.Items;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

public class ItemFactoryTest extends TestCase {

    @Test
    public void testGetNewInstance() {
        Item item = ItemFactory.getNewInstance("Knife");
        Item item1 = new Item("Knife", "A short, sharp blade.",
                Set.of(new Craft(Map.of("Wood", 1, "Metal", 1))));

        assertEquals(item1, item);
    }
}