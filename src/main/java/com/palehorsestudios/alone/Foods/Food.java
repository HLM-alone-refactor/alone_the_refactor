package com.palehorsestudios.alone.Foods;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "food")
@XmlAccessorType(XmlAccessType.FIELD)
public class Food {

    private String name;
    private double caloriesPerGram;
    private double grams;

    Food(String name, double caloriesPerGram, double grams) {
        this.name = name;
        this.caloriesPerGram = caloriesPerGram;
        this.grams = grams;
    }

    Food() {
        // do nothing
    }

    Food(Food food) {
        this(food.toString(), food.getCaloriesPerGram(), food.getGrams());
    }

    public double getCaloriesPerGram() {
        return caloriesPerGram;
    }

    public double getGrams() {
        return grams;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Double.compare(food.getCaloriesPerGram(), getCaloriesPerGram()) == 0 &&
                Double.compare(food.getGrams(), getGrams()) == 0 &&
                Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getCaloriesPerGram(), getGrams());
    }
}
