package com.palehorsestudios.alone.Items;

import com.palehorsestudios.alone.xmltesting_delme.Product;
import junit.framework.TestCase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;

public class ItemTest extends TestCase {
    Item item;

    public void testtestXmlToObject() throws JAXBException, FileNotFoundException {
        File file = new File("resources/items/items.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Item.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        item = (Item) unmarshaller.unmarshal(file);

        System.out.println(item);
    }

}