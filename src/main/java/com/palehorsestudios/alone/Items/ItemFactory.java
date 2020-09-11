package com.palehorsestudios.alone.Items;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.NoSuchElementException;

public class ItemFactory {

    private static Items all_items = getGameItems();

    private ItemFactory() {

    }

    private static Items getGameItems() {
        Items items = new Items();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Items.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            items = (Items) jaxbUnmarshaller.unmarshal(new File("resources/items/items.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return items;
    }

    public static Item getNewInstance(String type) throws IllegalArgumentException {
        return new Item(all_items.getItems()
                .stream()
                .filter(e -> e.getType().equals(type))
                .findAny()
                .orElseThrow(IllegalArgumentException::new));
    }
}
