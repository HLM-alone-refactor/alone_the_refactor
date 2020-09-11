package com.palehorsestudios.alone.Items;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * Set as package private to only be usable in ItemFactory
 */
@XmlRootElement(name = "items")
class Items {

    @XmlElement(name = "item")
    private Set<Item> items;

    Items() {
        // do nothing
    }

    Set<Item> getItems() {
        return items;
    }

}
