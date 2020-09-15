package com.palehorsestudios.alone.xmltesting_delme;

import junit.framework.TestCase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;

public class ProductsTest extends TestCase {
    private Product product;

    public void testtestXmlToObject() throws JAXBException, FileNotFoundException {
        File file = new File("src/main/java/com/palehorsestudios/alone/xmltesting_delme/temp.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        product = (Product) unmarshaller.unmarshal(file);

        System.out.println(product);
    }
}