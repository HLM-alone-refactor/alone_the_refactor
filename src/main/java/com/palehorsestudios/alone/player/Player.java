package com.palehorsestudios.alone.player;

import com.google.common.base.Objects;
import com.palehorsestudios.alone.Foods.Food;
import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.Items.ItemFactory;
import com.palehorsestudios.alone.Shelter;
import com.palehorsestudios.alone.util.HelperMethods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    // serializable requirment
    private static final long serialVersionUID = 1L;
    // static constants
    private static final int MIN_HYDRATION = 0;
    private static final int MAX_HYDRATION = 20;
    private static final int MIN_WEIGHT = 0;
    private static final int MIN_MORALE = 0;
    private static final int MAX_MORALE = 20;
    private static final double CALORIES_PER_POUND = 285.7;
    private final List<Item> items;
    private final Shelter shelter;
    // instance vars
    private int hydration;
    private double weight;
    private int morale;
    private boolean isRescued;

    /**
     * Public Player constructor.
     *
     * @param items Set of Items to be added to Player Shelter.
     */
    public Player(List<Item> items) {
        this.isRescued = false;
        this.hydration = 15;
        this.weight = 180;
        this.morale = 15;
        this.items = new ArrayList<>();
        this.shelter = new Shelter();
        for (Item item : items) {
            this.shelter.addEquipment(item, 1);
        }

        // boost shelter if have any item of these item groups
        List<List<Item>> shelter_boosts = List.of(
                ItemFactory.getNewInstances("Flint and Steel", "Matches", "Lighter"), // fire starting items
                ItemFactory.getNewInstances("Tarp"), // has a tarp
                ItemFactory.getNewInstances("Parachute Chord", "Axe", "Hatchet", "Shovel", "Knife"), // items to assist in shelter construction
                ItemFactory.getNewInstances("Sleeping Gear"), // has sleeping gear
                ItemFactory.getNewInstances("Pot", "Flashlight", "Batteries", "Survival Manual") // "nice to have" items
        );
        shelter_boosts.forEach(e -> {
            if (items.stream().anyMatch(e::contains)) {
                shelter.setIntegrity(shelter.getIntegrity() + 1);
            }
        });
    }

    // getters

    /**
     * Getter for Player hydration.
     *
     * @return Player hydration.
     */
    public int getHydration() {
        return hydration;
    }

    /**
     * Setter for Player hydration.
     *
     * @param hydration value for Player hydration.
     */
    public void setHydration(int hydration) {
        this.hydration = hydration < MIN_HYDRATION ? MIN_HYDRATION : Math.min(hydration, MAX_HYDRATION);
    }

    /**
     * Getter for Player weight.
     *
     * @return Player weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Setter for Player weight.
     *
     * @param weight value for Player weight.
     */
    public void setWeight(double weight) {
        this.weight = Math.max(MIN_WEIGHT, weight);
    }

    // setters

    /**
     * Getter for Player morale.
     *
     * @return Player morale.
     */
    public int getMorale() {
        return morale;
    }

    /**
     * Getter for items Player is currently carrying.
     *
     * @return ImmutableSet of Player items.
     */
    public List<Item> getItems() {
        return this.items;
    }

    /**
     * Getter for Player's shelter.
     *
     * @return Player's shelter.
     */
    public Shelter getShelter() {
        return this.shelter;
    }

    /**
     * Setter for Player morale.
     *
     * @param morale value for Player morale.
     */
    public void updateMorale(int morale) {
        this.morale += morale;
        this.morale = this.morale < MIN_MORALE ? MIN_MORALE : Math.min(this.morale, MAX_MORALE);
    }

    public void updateHydration(int hydration) {
        this.hydration += hydration;
        this.hydration = this.hydration < MIN_HYDRATION ? MIN_HYDRATION : Math.min(this.hydration, MAX_HYDRATION);
    }

    // business methods

    /**
     * Helper method for updating Player weight depending on the calories produced or expended
     * during a Player activity.
     *
     * @param calories Calories produced or expended during a Player activity.
     */
    public void updateWeight(double calories) {
        this.weight += HelperMethods.round(calories / CALORIES_PER_POUND, 1);
    }

    public boolean isDead() {
        boolean gameOver = false;
        if (this.weight < 180.0 * 0.6) {
            gameOver = true;
        } else if (this.morale <= 0) {
            gameOver = true;
        } else if (this.hydration <= 0) {
            gameOver = true;
        }
        return gameOver;
    }

    public void setRescued(boolean isRescued) {
        this.isRescued = isRescued;
    }

    public boolean isRescued(int days) {
        boolean isRescued = false;
        if (days > 15) {
            isRescued = ((int) Math.floor(Math.random() * 2)) != 0;
        }
        return isRescued;
    }

    public boolean isRescued() {
        return this.isRescued;
    }

    /**
     * Player toString override.
     *
     * @return String representation of the Player.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nCURRENT STATUS\n")
                .append("hydration: ").append(hydration)
                .append(", weight: ").append(weight)
                .append(", morale: ").append(morale)
                .append("\nItems: ");
        for (Item item : items) {
            sb.append(item).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length() - 1);
        sb.append("\nShelter - ")
                .append("integrity: ").append(this.getShelter().getIntegrity())
                .append(", firewood: ").append(this.getShelter().getFirewood())
                .append(", water: ").append(this.getShelter().getWaterTank())
                .append("\nFood Cache");
        for (Food food : this.getShelter().getFoodCache().keySet()) {
            sb.append("\n  ").append(food).append(": ");
            double foodWeightInGrams = HelperMethods.round(this.getShelter().getFoodCache().get(food), 1);
            sb.append(HelperMethods.getLargestFoodUnit(foodWeightInGrams));
        }
        sb.append("\nItems in Shelter");
        for (Item item : this.getShelter().getEquipment().keySet()) {
            int itemCount = this.getShelter().getEquipment().get(item);
            sb.append("\n  ").append(item).append(": ").append(itemCount);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return hydration == player.hydration &&
                Double.compare(player.weight, weight) == 0 &&
                morale == player.morale &&
                Objects.equal(items, player.items) &&
                Objects.equal(shelter, player.shelter);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(items, shelter, hydration, weight, morale);
    }
}
