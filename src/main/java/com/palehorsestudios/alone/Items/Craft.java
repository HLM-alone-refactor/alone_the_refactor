package com.palehorsestudios.alone.Items;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@XmlRootElement(name = "craft")
public class Craft implements Serializable {

    // serializable requirment
    private static final long serialVersionUID = 3L;

    @XmlElementWrapper(name = "requirements")
    private Map<String, Integer> requires;

    public Craft(Map<String, Integer> requires) {
        this.requires = requires;
    }

    public Craft() {

    }

    public void setRequires(Map<String, Integer> requires){
        this.requires = requires;
    }

    public Map<String, Integer> getRequirements() {
        return this.requires;
    }

    public boolean isCraftable() {
        return this.requires != null;
    }

    @Override
    public String toString() {
        return "Craft{" +
                "requirements='" + this.requires + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Craft craft = (Craft) o;
        boolean crafts = Objects.equals(getRequirements(), craft.getRequirements());
        return Objects.equals(getRequirements(), craft.getRequirements());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequirements());
    }


}
