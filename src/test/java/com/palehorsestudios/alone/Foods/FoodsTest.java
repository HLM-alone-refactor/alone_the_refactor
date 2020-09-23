package com.palehorsestudios.alone.Foods;

import junit.framework.TestCase;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Set;

public class FoodsTest extends TestCase {

    @Test
    public void testGetFoods() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Foods.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        //We had written this file in marshalling example
        Foods foods = (Foods) jaxbUnmarshaller.unmarshal( new File("src/test/resources/foods/foods.xml") );

        Set<Food> realFoods = Set.of(new Food("Fish", 0.84, 3401.94),
                new Food("Squirrel", 1.2, 340.1943));

        assertEquals(realFoods, foods.getFoods());
    }
}