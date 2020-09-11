package com.palehorsestudios.alone.Items;

import junit.framework.TestCase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ItemsTest extends TestCase {

    public void testGetItems() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Items.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        //We had written this file in marshalling example
        Items items = (Items) jaxbUnmarshaller.unmarshal( new File("resources/items/items.xml") );

        for(Item item : items.getItems()) {
            System.out.println(item);
        }
    }
}