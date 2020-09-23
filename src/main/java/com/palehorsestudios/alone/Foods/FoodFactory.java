package com.palehorsestudios.alone.Foods;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Set;

public class FoodFactory {

    private static Foods all_food = getGameFoods();

    private FoodFactory() {
        // do nothing
    }

    private static Foods getGameFoods() {
        Foods foods = new Foods();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Foods.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            foods = (Foods) jaxbUnmarshaller.unmarshal(new File("resources/items/foods.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return foods;
    }

    public static Food getNewInstance(String type) throws IllegalArgumentException {
        return new Food(all_food.getFoods()
                .stream()
                .filter(e -> e.toString().equals(type))
                .findAny()
                .orElseThrow(IllegalArgumentException::new));
    }

    public static Set<Food> getAllFood() {
        return Set.copyOf(all_food.getFoods());
    }

}
