package com.palehorsestudios.alone.Items;

import junit.framework.TestCase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Map;
import java.util.Set;

public class ItemTest extends TestCase {
    Item item;

    @Override
    public void setUp() throws Exception {
        File file = new File("src/test/resources/items/item.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Item.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        item = (Item) unmarshaller.unmarshal(file);
    }

    public void testXmlToObject() {
        Item item1 = new Item("knife", "A sharp, short blade.", 5.0,
                Set.of(new Craft(Map.of("steel", 1, "wood", 2)),
                new Craft(Map.of("iron", 1, "wood", 2))));
        assertEquals(item1, item);
    }

}