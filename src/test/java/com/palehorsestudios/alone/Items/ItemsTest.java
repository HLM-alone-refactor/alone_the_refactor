package com.palehorsestudios.alone.Items;

import junit.framework.TestCase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Map;
import java.util.Set;

public class ItemsTest extends TestCase {
    Items items;

    @Override
    public void setUp() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Items.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        //We had written this file in marshalling example
        items = (Items) jaxbUnmarshaller.unmarshal(new File("src/test/resources/items/items.xml"));
    }

    public void testGetItems() throws JAXBException {
        Item item1 = new Item("knife", "A sharp, short blade.",
                Set.of(new Craft(Map.of("steel", 1, "wood", 2)),
                        new Craft(Map.of("iron", 1, "wood", 2))));
        Item item2 = new Item("sword", "A sharp, long blade.", null);

        Set<Item> testItems = Set.of(item1, item2);

        assertEquals(testItems, items.getItems());

    }
}