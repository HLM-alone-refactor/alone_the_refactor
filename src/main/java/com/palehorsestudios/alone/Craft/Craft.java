package com.palehorsestudios.alone.Craft;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement(name = "craft")
public class Craft {

    @XmlElementWrapper(name = "requirements")
    private Map<String, Long> requires;

    public Craft(Map<String, Long> requires) {
        this.requires = requires;
    }

    public Craft() {

    }

    public void setRequires(Map<String, Long> requires){
        this.requires = requires;
    }

    @Override
    public String toString() {
        return "Craft{" +
                "requirements='" + requires + '\'' +
                '}';
    }
}
