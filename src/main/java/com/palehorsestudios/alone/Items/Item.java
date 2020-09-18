package com.palehorsestudios.alone.Items;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item implements Serializable {

    // serializable requirement
    private static final long serialVersionUID = 2L;

    private String type;
    private String description;
    private Set<Craft> craft;
    private Set<String> synonym;
    private double weight;

    /**
     * Set as package private to only be usable in ItemFactory
     * @param type
     * @param description
     */
    Item(String type, String description, double weight, Set<Craft> craft, Set<String> synonym) {
        this(type, description, weight, craft);
        this.synonym = synonym;
    }

    Item(String type, String description, double weight, Set<Craft> craft) {
        this.type = type;
        this.description = description;
        this.weight = weight;
        this.craft = craft;
    }

    /**
     * Set as package private to only be usable in ItemFactory
     */
    Item() {
        // do nothing
    }

    /**
     * Set as package private to only be usable in ItemFactory
     *
     * Copies the contents from the given item into a new instance.
     * @param item
     */
    Item(Item item) {
        this(item.getType(), item.getDescription(), item.getWeight(), item.getCraft(), item.getSynonym());
    }


    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Set<Craft> getCraft() {
        return Objects.requireNonNullElse(craft, Set.of());
    }

    public Set<String> getSynonym() {
        return Objects.requireNonNullElse(synonym, Set.of());
    }

    public double getWeight() {
        return Objects.requireNonNullElse(weight, 1.0);
    }

    public boolean isCraftable() {
        return (craft != null) && craft.size() > 0;
    }

    @Override
    public String toString() {
        return "Item{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", craft=" + craft +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(getType(), item.getType()) &&
                Objects.equals(getDescription(), item.getDescription()) &&
                Objects.equals(getCraft(), item.getCraft());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getDescription(), getCraft());
    }
}
