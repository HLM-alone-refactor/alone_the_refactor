package com.palehorsestudios.alone.Foods;

import com.palehorsestudios.alone.Items.Item;
import junit.framework.TestCase;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class FoodTest extends TestCase {

    Food food;

    @Override
    public void setUp() throws Exception {
        File file = new File("src/test/resources/foods/food.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Food.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        food = (Food) unmarshaller.unmarshal(file);
    }

    @Test
    public void testXmlToObject() {
        Food foodReal = new Food("Fish", 0.84, 3401.94);

        assertEquals(foodReal, food);
    }
}