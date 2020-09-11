package com.palehorsestudios.alone.Items;

import junit.framework.TestCase;

public class ItemFactoryTest extends TestCase {

    public void testGetNewInstance() {
        Item item = ItemFactory.getNewInstance("knife");
        System.out.println(item);
    }
}