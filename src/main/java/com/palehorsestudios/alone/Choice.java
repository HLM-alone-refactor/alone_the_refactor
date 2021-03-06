package com.palehorsestudios.alone;

import com.palehorsestudios.alone.Foods.Food;
import com.palehorsestudios.alone.Items.Item;
import com.palehorsestudios.alone.player.Player;

import java.util.Objects;

public class Choice {
    private String keyword;
    private Item item;
    private Food food;
    private Player player;

    public Choice(String keyword, Player player) {
        this(keyword, player, null, null);
    }

    public Choice(String keyword, Player player, Food food) {
        this(keyword, player, null, food);
    }

    public Choice(String keyword, Player player, Item item) {
        this(keyword, player, item, null);
    }

    public Choice(String keyword, Player player, Item item, Food food) {
        this.keyword = keyword;
        this.player = player;
        this.item = item;
        this.food = food;
    }

    public Player getPlayer() {
        return player;
    }

    public String getKeyword() {
        return keyword;
    }

    public Item getItem() {
        return item;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "keyword='" + keyword + '\'' +
                ", item=" + item +
                ", food=" + food +
                ", player=" + player +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Choice choice = (Choice) o;
        return Objects.equals(getKeyword(), choice.getKeyword()) &&
                Objects.equals(getItem(), choice.getItem()) &&
                Objects.equals(getFood(), choice.getFood()) &&
                Objects.equals(getPlayer(), choice.getPlayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKeyword(), getItem(), getFood(), getPlayer());
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Choice choice = (Choice) o;
//        return Objects.equal(keyword, choice.keyword) &&
//                item.equals(choice.item) &&
//                food.equals(choice.food) &&
//                Objects.equal(player, choice.player);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(keyword, item, food, player);
//    }
}
