package com.palehorsestudios.alone.Foods;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "foods")
class Foods {

    @XmlElement(name = "food")
    private Set<Food> foods;

    Foods() {
        // do nothing
    }

    Set<Food> getFoods() {
        return foods;
    }
}
