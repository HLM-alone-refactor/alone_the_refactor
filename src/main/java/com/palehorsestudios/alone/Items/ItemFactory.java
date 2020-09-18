package com.palehorsestudios.alone.Items;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemFactory {

    private static final Items ALL_ITEMS = getGameItems();

    private ItemFactory() {
        // do nothing for static class
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

        // make sure all items in game are available
        // throw nullPointerException if one was not found
        Set<String> itemNames = items.getItems().stream().map(Item::getType).collect(Collectors.toSet());
        for (Item item : items.getItems()) {
            for (Craft craft : item.getCraft()) {
                craft.getRequirements().forEach( (s, integer) -> {
                    if (!itemNames.contains(s)) {
                        throw new NullPointerException("Cannot find item " + s + " in item xml file");
                    }
                });
            }
        }

        return items;
    }

    public static Item getNewInstance(String type) throws IllegalArgumentException {
        return new Item(ALL_ITEMS.getItems()
                .stream()
                .filter(e -> e.getType().equals(type))
                .findAny()
                .orElseThrow(IllegalArgumentException::new));
    }

    public static List<Item> getNewInstances(String ... type) throws IllegalArgumentException {
        List<Item> result = new ArrayList<>();
        for (String s : type) {
            result.add(getNewInstance(s));
        }
        return result;
    }

    public static Set<Item> getAllItems() {
        return Set.copyOf(ALL_ITEMS.getItems());
    }

}
